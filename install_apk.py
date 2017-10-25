#!/usr/bin/env python
# encoding: utf-8

"""
@author: xiaoq
@software: PyCharm
@file: adb.py
@time: 2017/10/25 下午2:53
"""

import os

apk_path = "../debug_pack/codoonSportsPlus_App_v540/build/outputs/apk/"

def is_devices_ready():
    output = os.popen('adb devices -l')
    context = output.readlines()
    udid_device = ["e6a29a3a"]
    for udid in udid_device:
        if len([x for x in context if udid in x]) > 0:
            return True
    return False

if is_devices_ready():
    if os.path.exists(apk_path):
        os.system("adb install -r %s/*.apk" % apk_path)
    else:
        with open("error.log", 'w') as f:
            f.write("no apk")
else:
    with open("error.log",'w') as f:
        f.write("no devices")



