from django.conf.urls import url

from . import views

app_name = 'banking_system'

urlpatterns = [
    url(r'^$', views.home, name='home'),
    url(r'^register/$',views.register,name='register'),
    url(r'^dashboard/$',views.dashboard,name='dashboard'),
    url(r'^logout/$',views.user_logout,name='logout'),
    url(r'^profile/$',views.profile,name='profile'),
    url(r'^billpay/$',views.billpay,name='billpay'),
    url(r'^billpay_addbill/$',views.billpay_addbill,name='billpay_addbill'),
    url(r'^billpay_company_w_acc/$',views.billpay_company_w_acc,name='billpay_company_w_acc'),
    url(r'^billpay_company_receipt/$',views.billpay_company_receipt,name='billpay_company_receipt'),
    url(r'^internal_transfer/$',views.internal_transfer,name='internal_transfer'),
    url(r'^internal_transfer/receipt$',views.transfer_receipt,name='transfer_receipt'),


    url(r'^home2/$',views.home2,name='home2'),
    url(r'^login/$',views.user_login,name='user_login'),
    url(r'^change_login_pin$',views.change_login_pin,name='change_login_pin'),

    # url(r'^billpay_company$',views.billpay_company,name='billpay_company'),
    url(r'^external_transfer/$',views.external_transfer,name='external_transfer'),
    url(r'^external_transfer/receipt$',views.external_transfer_receipt,name='external_transfer_receipt'),
]
