# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect
from banking_system.forms import UserForm, UserProfileInfoForm, UserUpdateForm

from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from django.core.urlresolvers import reverse
from django.contrib.auth.models import User
from banking_system.models import UserProfileInfo

import backend_client

def home(request):
	if request.method == 'GET':
		return render(request, 'home.html')

	else:
		return user_login(request)

def register(request):

	registered = False

	if request.method == 'POST':

		user_form = UserForm(data=request.POST)
		profile_form = UserProfileInfoForm(data=request.POST)
		# Check to see both forms are valid
		if user_form.is_valid() and profile_form.is_valid():
			# Save User Form to Database
			user = user_form.save()
			# Hash the password
			user.set_password(user.password)
			# Update with Hashed password
			user.save()
			profile = profile_form.save(commit=False)
			profile.user = user
			profile.save()
			# Registration Successful!
			registered = True
		else:
			# One of the forms was invalid if this else gets called.
			print(user_form.errors, profile_form.errors)
	else:
			# Was not an HTTP post so we just render the forms as blank.
		user_form = UserForm()
		profile_form = UserProfileInfoForm()
			# This is the render and context dictionary to feed
			# back to the registration.html file page.
	return render(request, 'register2.html', {'user_form': user_form,
											   	 'profile_form': profile_form,
											   	 'registered': registered})

def user_login(request):
	if request.method == 'POST':
		username = request.POST.get('username')
		password = request.POST.get('password')

		user = authenticate(username=username, password=password)
		if user:
			login(request, user)
			return HttpResponseRedirect(reverse('banking_system:dashboard'))
		else:
			return render(request, 'home.html', {'invalid':'Invalid username or password'})

@login_required
def dashboard(request):
<<<<<<< HEAD

	balances = backend_client.getAccountInfo(request.user.id)
=======
	balances = backend_client.getAccountInfo(request.user.id)
	print balances
>>>>>>> pending
	return render(request, 'dashboard.html', {'balances': balances})

@login_required
def user_logout(request):
	logout(request)
	return HttpResponseRedirect(reverse('banking_system:home'))

@login_required
def profile(request):
	success = False
	user_profile = UserProfileInfo.objects.get(user__pk=request.user.id)

	if request.method == 'POST':
		profile_form = UserProfileInfoForm(data=request.POST)
		update_form = UserUpdateForm(data=request.POST)

		if profile_form.is_valid() and update_form.is_valid():
			new_profile = profile_form.save(commit = False)
			new_user = update_form.save(commit = False)

			user_profile.user.email = new_user.email
			user_profile.user.first_name = new_user.first_name
			user_profile.user.last_name = new_user.last_name
			user_profile.address = new_profile.address
			user_profile.phone_num = new_profile.phone_num

			user_profile.user.save()
			user_profile.save()
			success = True
		else:
			print(profile_form.errors)
	else:
		update_form = UserUpdateForm(instance=user_profile.user)
		profile_form = UserProfileInfoForm(instance=user_profile)

	return render(request, 'profile.html', {'update_form': update_form,
											'profile_form': profile_form})


@login_required
def billpay(request):
	return render(request, 'billpay_welcomepage.html')

@login_required
def billpay_addbill(request):
	return render(request, 'billpay_selecttype.html')

@login_required
def billpay_company_w_acc(request):
	return render(request, 'billpay_company_final_step.html')
