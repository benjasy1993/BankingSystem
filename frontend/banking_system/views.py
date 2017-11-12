# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect
from banking_system.forms import UserForm, UserProfileInfoForm

from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from django.core.urlresolvers import reverse

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
		return render(request,'register.html',
		{'user_form':user_form,
		'registered':registered})

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
	return render(request, 'dashboard.html')

@login_required
def user_logout(request):
	logout(request)
	return HttpResponseRedirect(reverse('banking_system:home'))

@login_required
def profile(request):

	if request.method == 'POST':
		profile_form = UserProfileInfoForm()

	else:
		profile_form = UserProfileInfoForm()

		return render(request, 'profile.html', {'profile_form': profile_form})
