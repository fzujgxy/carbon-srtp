import os

from django.conf import settings
from django.http import response
from django.shortcuts import render, HttpResponse
import numpy as np
import pandas as pd
from requests import Response

from sklearn.linear_model import LinearRegression, Lasso
from sklearn.model_selection import train_test_split as TTS
import matplotlib.pyplot as plt
from sklearn.linear_model import LassoCV

from rest_framework.views import APIView
from backends import settings
from django.shortcuts import render, redirect, HttpResponse
from django.http import JsonResponse
from rest_framework import status
import os
import uuid


def runlasso(Request):
    # def lassoStep(path):
    # D:/s072007124Python/SRTP/now.xlsx
    # excelPath=input('请输入文件路径')
    excelPath = 'D:/SRTP/carbon-srtp/Java/data/datas.xlsx'
    x_value = pd.read_excel(excelPath)
    # x_value.shape[0] 获取行数,即有几年
    # x_value.shape[1] 获取列数,即有几个指标
    CharacteristicNum = x_value.shape[1] - 1
    x = pd.DataFrame(x_value.iloc[:, 0:CharacteristicNum])
    y = np.array(x_value.iloc[:, -1] / 1000)

    # 划分训练集和测试集
    xtrain, xtest, ytrain, ytest = TTS(x, y, test_size=0.3, random_state=420)

    for i in [xtrain, xtest]:
        i.index = range(i.shape[0])

    # 线性回归进行拟合
    # reg = LinearRegression().fit(xtrain, ytrain)
    # (reg.coef_ * 100).tolist()
    # print('线性回归拟合结果为:', (reg.coef_ * 100).tolist())

    # lasso拟合
    Lasso_ = Lasso(alpha=0).fit(xtrain, ytrain)
    (Lasso_.coef_ * 100).tolist()
    print('alpha=0时，lasso拟合结果为：', (Lasso_.coef_ * 100).tolist())

    # # 加大正则项系数，观察模型系数变化
    # Lasso_ = Lasso(alpha=1).fit(xtrain, ytrain)
    # (Lasso_.coef_ * 100).tolist()
    # print('alpha=1时，lasso拟合结果为：', (Lasso_.coef_ * 100).tolist())

    # 加大正则项系数，观察模型系数变化
    # Lasso_ = Lasso(alpha=100).fit(xtrain, ytrain)
    # (Lasso_.coef_ * 100).tolist()
    # print('alpha=100时，lasso拟合结果为：', (Lasso_.coef_ * 100).tolist())


       # 自己建立Lasso进行alpha选择的范围
    alpharange = np.logspace(-10, -2, 200, base=10)
    # # 其实是形成10为底的指数函数
    # # 10**（-10）到10**（-2）
    lasso_ = LassoCV(alphas=alpharange, cv=5).fit(xtrain, ytrain)


    # 将系数进行绘图
    # plt.plot(range(1,10),(reg.coef_*100).tolist(),color='red',label='LR')
    # plt.plot(range(1,10),(Ridge_.coef_*100).tolist(),color='orange',label='Ridge')
    plt.plot(range(CharacteristicNum), (lasso_.coef_).tolist(), color='blue', label='Lasso')
    # plt.plot(range(1, 9), [0] * 8, color='grey', linestyle="--")
    plt.xlabel('w')  # 横坐标是每个特征对应的系数
    # plt.legend()
    plt.savefig('D:/SRTP/carbon-srtp/Java/data/alpha.jpg')
    # plt.show()



    # 查看被选出来的最佳正则化系数
    # print('被选出来的最佳正则化系数是:', lasso_.alpha_)

    # 调用所有交叉验证的结果
    lasso_.mse_path_

    # 返回每个alpha下的五折交叉验证结果
    lasso_.mse_path_.shape

    # print()

    score = lasso_.score(xtest, ytest)



    # 输出结果
    result = []
    elementResult = []
    alphaResult = []
    finalResult = []
    dataResult = []

    for i in range(x_value.shape[1] - 1):
        dataResult.append(x_value.columns.values[i]+':'+str(lasso_.coef_[i]))


    for i in range(x_value.shape[1] - 1):
        if lasso_.coef_[i] != 0:
            finalResult.append(x_value.columns.values[i])

    for i in range(20):
        alphaResult.append(lasso_.alphas_[i])

    for i in range(x_value.shape[1] - 1):
        if lasso_.coef_[i] != 0:
            elementResult.append(x_value.columns.values[i])

    # [0]
    result.append(finalResult)

    # [1]
    result.append(score)

    # [2]
    result.append(dataResult)

    # [3]
    result.append(alphaResult)

    # [4]
    result.append(elementResult)
    return JsonResponse(result, safe=False)