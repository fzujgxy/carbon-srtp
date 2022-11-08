import numpy as np
import pandas as pd
import operator
import math

from django.http import JsonResponse


def runEbrb(request):
    data = pd.read_excel('D:/SRTP/carbon-srtp/Java/data/EbrbDatas.xlsx', header=None)
    test = pd.read_excel('D:/SRTP/carbon-srtp/Java/data/EbrbTest.xlsx', header=None)
    req=request.GET
    N=(int)(req["N"])
    row = data.columns.size  # 列数
    line = data.shape[0]  # 行数
    line_test = test.shape[0]  # 行数
    combine = data.append(test)
    combineData = combine.values[:, 0:-1]

    # 第j列的u u[j][n] 表示第j列的第n个评价指标的效用值 n[][0]=min n[][N]=max
    u = np.zeros((row - 1, N))
    for j in range(0, row - 1):
        for n in range(0, N):
            min_index, u[j][0] = min(enumerate(combineData[:, j]), key=operator.itemgetter(1))
            max_index, u[j][N - 1] = max(enumerate(combineData[:, j]), key=operator.itemgetter(1))
            step = (u[j][N - 1] - u[j][0]) / (N - 1)
            for p in range(1, n):
                u[j][p] = u[j][p - 1] + step

    uD=np.zeros(N)
    for n in range(0,N):
        min_index, uD[0] = min(enumerate(data.values[:,-1]), key=operator.itemgetter(1))
        max_index, uD[N-1] = max(enumerate(data.values[:,-1]), key=operator.itemgetter(1))
        step=(uD[N-1]-uD[0])/(N-1)
        for p in range(1,n):
            uD[p]=uD[p-1]+step

    def alpha(Matrix, u):
        row = Matrix.columns.size  # 列数
        line = Matrix.shape[0]  # 行数
        alpha = np.zeros((line, row, N))
        x = Matrix.values
        for j in range(0, row - 1):  # 列
            for i in range(0, line):  # 行
                for k in range(0, N):  # 评价等级个数
                    if x[i][j] >= u[j][k] and x[i][j] <= u[j][k + 1]:
                        alpha[i][j][k] = (u[j][k + 1] - x[i][j]) / (u[j][k + 1] - u[j][k])
                        alpha[i][j][k + 1] = 1 - alpha[i][j][k]
                        break
        return alpha

    Beta_Alpha = np.zeros((line, N))
    for i in range(0, line):
        for j in range(0, N):
            if data.values[i][-1] >= uD[j] and data.values[i][-1] <= uD[j + 1]:
                Beta_Alpha[i][j] = (uD[j + 1] - data.values[i][-1]) / (uD[j + 1] - uD[j])
                Beta_Alpha[i][j + 1] = 1 - Beta_Alpha[i][j]
                break

    alpha_data = alpha(data, u)[:, 0:-1]
    alpha_test = alpha(test, u)

    # 第m个测试集，的第j个数据对于[i，j]的S
    S = np.zeros((line_test, row - 1, line))
    for m in range(0, line_test):  # 测试集有几行
        for j in range(0, row - 1):  # 列
            for i in range(0, line):  # 行
                underSqrtCircle = 0
                for k in range(0, N):
                    underSqrtCircle += (alpha_test[m][j][k] - alpha_data[i][j][k]) ** 2
                S[m][j][i] = 1 - math.sqrt(underSqrtCircle / 2)

    W_Upper = np.zeros((line_test, line))
    for p in range(0, line_test):
        for q in range(0, line):
            multiplication = 1
            for j in range(0, row - 1):
                multiplication *= S[p][j][q]
            W_Upper[p][q] = multiplication

    W = np.zeros((line_test, line))
    for m in range(0, line_test):
        sum = 0
        for n in range(0, line):
            sum += W_Upper[m][n]
        for n in range(0, line):
            W[m][n] = W_Upper[m][n] / sum

    # 第m个测试集，第n个评价等级
    def Beta_Upper_Front(m, n):
        MultiplicationResult = 1
        for k in range(0, line):
            MiniResult = W[m][k] * Beta_Alpha[k][n] + 1 - W[m][k]
            MultiplicationResult *= MiniResult
        return MultiplicationResult

    # 第m个测试集
    def Beta_Upper_Backer(m):
        MultiplicationResult = 1
        for k in range(0, line):
            MiniResult = 1 - W[m][k]
            MultiplicationResult *= MiniResult
        return MultiplicationResult

    # 第m个测试集
    def Beta_Lower_Front(m):
        sum = 0
        for i in range(0, N):
            sum += Beta_Upper_Front(m, i)
        return sum

    # 第m个测试集
    def Beta_Lower_Backer(m):
        MultiplicationResult = 1
        for k in range(0, line):
            MiniResult = 1 - W[m][k]
            MultiplicationResult *= MiniResult
        return N * MultiplicationResult

    Beta = np.zeros((line_test, N))
    for m in range(0, line_test):
        for n in range(0, N):
            Beta[m][n] = (Beta_Upper_Front(m, n) - Beta_Upper_Backer(m)) / (Beta_Lower_Front(m) - Beta_Lower_Backer(m))

    def FrontResult(m):
        FrontResult = 0
        for i in range(0, N):
            FrontResult += (uD[i]) * Beta[m, i]
        return FrontResult

    def BackerResult(m):
        BackerResult = 0
        for i in range(0, N):
            BackerResult += Beta[m, i]
        return 1 - BackerResult

    def FinalResult(m):
        Result = FrontResult(m) + ((uD[0] + uD[N - 1]) / 2) * BackerResult(m)
        return Result

    alphaResult = alpha_data.tolist()
    S_Result = S.tolist()
    W_Result = W.tolist()
    finalResult = []
    result = []


    for i in range(0, line_test):
        finalResult.append(FinalResult(i))

    # [0]
    result.append(alphaResult)

    # [1]
    result.append(S_Result)

    # [2]
    result.append(W_Result)

    # [3]
    result.append(finalResult)

    return JsonResponse(result, safe=False)