# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render

# Create your views here.
def index(request,url_text):
    if "//" in url_text:
        split=""
    else:
        split= url_text.split("/")

    tekst =""
    for item in split:
        tekst +=item+"--"

    return render(request, 'urls/index.html',{'tekst':tekst})
