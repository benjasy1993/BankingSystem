from django import forms
from django.contrib.auth.models import User
from banking_system.models import UserProfileInfo

class UserForm(forms.ModelForm):
	password = forms.CharField()

	class Meta():
		model = User
		fields = ('username', 'email', 'password', 'first_name', 'last_name')

class UserProfileInfoForm(forms.ModelForm):
	class Meta():
		model = UserProfileInfo
		fields = ('address', 'phone_num')

class UserUpdateForm(forms.ModelForm):
	class Meta():
		model = User
		fields = ('email', 'first_name', 'last_name')
