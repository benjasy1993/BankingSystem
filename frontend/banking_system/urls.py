from django.conf.urls import url

from . import views

app_name = 'banking_system'

urlpatterns = [
    url(r'^$', views.home, name='home'),
    url(r'^register/$',views.register,name='register'),
    url(r'^dashboard/$',views.dashboard,name='dashboard'),
    url(r'^logout/$',views.user_logout,name='logout'),
    url(r'^profile/$',views.profile,name='profile'),
]
