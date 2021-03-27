from tkinter import *
import hashlib
import time
import os
from PIL import Image, ImageFilter, ImageOps
import tkinter as tk
from tkinter import filedialog, dialog, messagebox

LOG_LINE_NUM = 0

class MY_GUI():
    def __init__(self,init_window_name):
        self.init_window_name = init_window_name


    #设置窗口
    def set_init_window(self):
        self.init_window_name.title("绘图处理工具_v1.0")           #窗口名
        #self.init_window_name.geometry('320x160+10+10')                         #290 160为窗口大小，+10 +10 定义窗口弹出时的默认展示位置
        self.init_window_name.geometry('534x400+500+60')
        #self.init_window_name["bg"] = "pink"                                    #窗口背景色，其他背景色见：blog.csdn.net/chl0000/article/details/7657887
        #self.init_window_name.attributes("-alpha",0.9)                          #虚化，值越小虚化程度越高
        #标签
        #上传
        #self.init_data_label = Label(self.init_window_name, text="待处理图片")
        #self.init_data_label.grid(row=0, column=0)

        self.btn = tk.Button(self.init_window_name, text='上传文件', command=self.upload_file)
        self.btn.grid(row=3, column=0, ipadx='3', ipady='3', padx='10', pady='20')
        self.updimg = tk.Entry(self.init_window_name, width='40')
        self.updimg.grid(row=3, column=1)
        #保存
        self.result_data_label = Label(self.init_window_name, text="输出图片")
        self.result_data_label.grid(row=6, column=0)

        #self.btn2 = tk.Button(self.init_window_name, text='保存文件', command=self.save_file)
        #self.btn2.grid(row=3, column=12, ipadx='3', ipady='3', padx='10', pady='20')
        #self.savimg = tk.Entry(self.init_window_name, width='40')
        #self.savimg.grid(row=3, column=12)

        self.log_label = Label(self.init_window_name, text="日志")
        self.log_label.grid(row=14, column=0)
        #文本框
        #self.init_data_Text = Text(self.init_window_name, width=67, height=35)  #原始数据录入框
        #self.init_data_Text.grid(row=1, column=0, rowspan=10, columnspan=10)
        #self.result_data_Text = Text(self.init_window_name, width=70, height=49)  #处理结果展示
        #self.result_data_Text.grid(row=1, column=12, rowspan=15, columnspan=10)
        self.log_data_Text = Text(self.init_window_name, width=75, height=15)  # 日志框
        self.log_data_Text.grid(row=16, column=0, columnspan=10)
        #按钮
        self.str_trans_to_handdraw_button = Button(self.init_window_name, command=self.str_trans_to_handdraw,
                                                   text="图片转手绘风格", bg="lightblue", width=15)  # 调用内部方法  加()为直接调用
        self.str_trans_to_handdraw_button.grid(row=6, column=1)




    #draw相关
    #返回区域
    def dodge(self, a, b, alpha):
        return min(int(a * 255 / (255 - b * alpha)), 255)

    #具体变换
    def draw(self, img, blur=25, alpha=1.0):
        img1 = img.convert('L')
        img2 = img1.copy()
        img2 = ImageOps.invert(img2)
        for i in range(blur):
            img2 = img2.filter(ImageFilter.BLUR)
        width, height = img1.size
        for x in range(width):
            for y in range(height):
                a = img1.getpixel((x, y))
                b = img2.getpixel((x, y))
                if a != 0:
                    img1.putpixel((x, y), min(int(a * 255 / (255 - b * alpha)), 255))
        img1.show()
        return img1

    def upload_file(self):
        self.updimg.delete(0, END)
        selectFile = tk.filedialog.askopenfilename()  # askopenfilename 1次上传1个；askopenfilenames1次上传多个
        self.updimg.insert(0, selectFile)
        #print(self.updimg.get())
        #return selectFile

    def save_file(self, img):
        filename1 = tk.filedialog.asksaveasfilename(initialfile=img)
        #with open(filename1, 'w') as f:
            #f.write(text.get(0.0, tk.END))
            #basename = os.path.basename(filename1)
            #save_succed = messagebox.showinfo(title='message', message='%s  saveas  succed' % basename)
        return filename1



    # 功能函数
    def str_trans_to_handdraw(self):
        src = self.updimg.get()
        #print('src', src)
        #print(self.updimg.get())
        if src:
            try:
                img = Image.open(src)
                img2 = self.draw(img)
                dist = self.save_file(img2)
                img2.save(dist)
                self.write_log_to_Text("INFO:str_trans_to_handdraw success")
            except:
                self.result_data_Text.insert(1.0, "图片转换失败")
        else:
            self.write_log_to_Text("ERROR:str_trans_to_handdraw failed")


    #获取当前时间
    def get_current_time(self):
        current_time = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
        return current_time


    #日志动态打印
    def write_log_to_Text(self,logmsg):
        global LOG_LINE_NUM
        current_time = self.get_current_time()
        logmsg_in = str(current_time) +" " + str(logmsg) + "\n"      #换行
        if LOG_LINE_NUM <= 7:
            self.log_data_Text.insert(END, logmsg_in)
            LOG_LINE_NUM = LOG_LINE_NUM + 1
        else:
            self.log_data_Text.delete(1.0,2.0)
            self.log_data_Text.insert(END, logmsg_in)


def gui_start():
    init_window = Tk()              #实例化出一个父窗口
    ZMJ_PORTAL = MY_GUI(init_window)
    # 设置根窗口默认属性
    ZMJ_PORTAL.set_init_window()

    init_window.mainloop()          #父窗口进入事件循环，可以理解为保持窗口运行，否则界面不展示


gui_start()