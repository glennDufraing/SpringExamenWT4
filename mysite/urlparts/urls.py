from urlparts import views
from django.conf.urls import url

app_name='answer'
urlpatterns = [
    url(r'(?P<url_text>[\w\-]+)', views.index,name='index'),

    ]
