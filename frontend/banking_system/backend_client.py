import requests

BACK_END_ENDPOINT = 'http://localhost:8001'
ACCOUNTS_URL = BACK_END_ENDPOINT + '/accounts'

def setUpAccount(user_id):
    r = requests.get(url=ACCOUNTS_URL + '/initiate', params={'userId':user_id})
    print r.status_code
    return r.text


## return a dict that contains balance of every account
def getAccountInfo(user_id):
    print user_id
    result = dict()
    r = requests.get(url=BACK_END_ENDPOINT + '/accounts/info', params={'userId':user_id})
    if r.text == '':
        result['checking'] = 'pending'
        result['saving'] = 'pending'
        result['growing'] = 'pending'
    else:
        json = r.json()
        result['checking'] = int(json['bankAccounts'][0]['balance'])
        result['saving'] = int(json['bankAccounts'][1]['balance'])
        result['growing'] = int(json['bankAccounts'][2]['balance'])
    return result

print setUpAccount(8)
