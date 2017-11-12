# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User
import datetime
# Create your models here.

class UserProfileInfo(models.Model):

	user = models.OneToOneField(User)
	address = models.CharField(blank=True, max_length=40)
	phone_num = models.CharField(blank=True, max_length=20)
	def __str__(self):
		return self.user.username
