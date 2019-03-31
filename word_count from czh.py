#! /usr/bin/env python
# -*- coding: utf-8 -*-
import re
#哈哈哈哈哈哈哈
def wordcount(filepath):
    file=open(filepath,'r')
    str_ori=file.read()
    list_ori=str_ori.strip().split()   #获取字符串于列表中
    items_appeared=[]
    result_list=[]
    #将出现过的item放入一个新的列表
    for item1 in list_ori:
        if item1 not in items_appeared:
            items_appeared.append(item1)
    #计算这些物品最长的长度，方便对齐
    the_longest=len(items_appeared[0])
    for i in range(0,len(items_appeared)):
        if len(items_appeared[i])>the_longest:
            the_longest=len(items_appeared[i])
    #按输出的形式加入到一个新的列表中
    for item in items_appeared:
        new_item=item + ':' +' '
        #变成字符串加冒号加空格的形式，对齐
        if len(item)<the_longest:
            new_item=new_item.ljust(the_longest+2)
        #在原列表中寻找重复的字符串
        for thing in list_ori:
            if thing==item:
                new_item=new_item+'*'
        result_list.append(new_item)  #获得了以最终输出的每一行作为元素的列表
    #列表中元素按照字典顺序排序
    result_list.sort()
    #列表中元素按长度排序
#    num_list=[len(one) for one in result_list]
#    final_list = []
#    for q in range(0, len(result_list)):
 #       result_list_and_len = (result_list[q], num_list[q])
 #       final_list.append(result_list_and_len)
  #  final_list.sort(key=lambda x: x[1], reverse=True)
#    for z in range(0, len(final_list)):
   #     str_and_len = final_list[z]
#        print(str_and_len[0])                                我的方法

    for i in range(0,len(result_list)-1):
        for k in range(0,len(result_list)-1-i):
            if len(result_list[k])<len(result_list[k+1]):
                temp=result_list[k]
                result_list[k]=result_list[k+1]
                result_list[k+1]=temp
    for result in result_list:
        print(result)
    return
if __name__=='__main__':
    wordcount('E:/git work/b738ae9d5fa544eabf89412bb6fd35aa/127-word-count/files/test2')
           