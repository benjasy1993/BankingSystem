from django import forms
from django.contrib.auth.models import User
from banking_system.models import UserProfileInfo

class UserForm(forms.ModelForm):
	password = forms.CharField()

	class Meta():
		model = User
		fields = ('username', 'email', 'password', 'first_name', 'last_name')
		widgets = {
            'username': forms.TextInput(attrs={'placeholder': 'User Name'}),
			'email': forms.TextInput(attrs={'placeholder': 'Email'}),
			'password': forms.TextInput(attrs={'placeholder': 'Password'}),
			'first_name': forms.TextInput(attrs={'placeholder': 'First Name'}),
			'last_name': forms.TextInput(attrs={'placeholder': 'Last Name'})
        }

class UserProfileInfoForm(forms.ModelForm):
	class Meta():
		model = UserProfileInfo
		fields = ('address', 'phone_num')
		widgets = {
			'address': forms.TextInput(attrs={'placeholder': 'Address'}),
			'phone_num': forms.TextInput(attrs={'placeholder': 'Phone Number'})
		}


class UserUpdateForm(forms.ModelForm):
	class Meta():
		model = User
		fields = ('email', 'first_name', 'last_name')
