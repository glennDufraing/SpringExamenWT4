# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render

# Create your views here.
def index(request,url_text):


    return render(request, 'urls/index.html',{'url_text':url_text})
