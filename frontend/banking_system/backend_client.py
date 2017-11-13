import requests

BACK_END_ENDPOINT = 'http://localhost:8001'
ACCOUNTS_URL = '/accounts'

def setUpAccount(user_id):
    requests.get(url=BACK_END_ENDPOINT + '/accounts', params={'userId':user_id}})

def getAccountInfo(user_id):
    requests.get(url=BACK_END_ENDPOINT + '/accounts/info', params={'userId':user_id}})
