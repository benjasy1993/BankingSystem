import requests
import datetime

BACK_END_ENDPOINT = 'http://localhost:8002'
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

def billpay_company_w_acc(user_id, toAccountNum, routine, amount, scheduledDate):
    result = dict()
    r = requests.get(url=BACK_END_ENDPOINT + '/accounts/info', params={'userId':user_id})
    print user_id
    if r.text != '':
        user_json = r.json()
        fromAccountId = int(user_json['bankAccounts'][0]['accountId'])

        r = requests.get(url=BACK_END_ENDPOINT + '/accounts/billers/search', params={'routingNum':routine, 'accountNum':toAccountNum})
        if r.text != '':
            biller_json = r.json()
            r = requests.get(url=BACK_END_ENDPOINT + '/billpay', params={'fromAccountId':fromAccountId, 'toAccountId': biller_json['bankAccountId'], 'amount': amount, 'scheduledDate': scheduledDate})
            if r.text != '':
                billpay_json = r.json()
                print billpay_json
                if billpay_json['status'] == "COMPLETED":
                    result['fromAccountId'] = fromAccountId
                    result['toAccountNum'] = biller_json['bankAccount']['accountNum']
                    result['routine'] = biller_json['bankAccount']['routingNum']
                    result['companyName'] = biller_json['companyName']
                    result['phoneNum'] = biller_json['phoneNum']
                    result['companyAddress'] = biller_json['address']
                    result['transNum'] = billpay_json['transactionId']
                    result['sendOnDate'] = scheduledDate
                    result['amount'] = amount
                    time = float(str(billpay_json['completedDate'])[:-3])
                    result['receiveDate'] = datetime.datetime.fromtimestamp(time).strftime('%Y-%m-%d %H:%M:%S')
                    print result
    return result

#print getAccountInfo(1)
#print billpay_company_w_acc(8, 3907153438, 2998444994, 20, "2017-11-16")
# return a dict that contains ids of all accounts
def getAccountBankId(user_id):
    r = requests.get(url=BACK_END_ENDPOINT + '/accounts/info', params={'userId':user_id})
    result = dict()
    if r.text == '':
        result['checking'] = 'pending'
        result['saving'] = 'pending'
        result['growing'] = 'pending'
    else:
        json = r.json()
        result['checking'] = int(json['bankAccounts'][0]['accountId'])
        result['saving'] = int(json['bankAccounts'][1]['accountId'])
        result['growing'] = int(json['bankAccounts'][2]['accountId'])
    return result

def makeTransfer(from_account_id, to_account_id, date, amount):
    result = dict()
    r = requests.get(url = BACK_END_ENDPOINT + '/transfer', params={
        'fromAccountId': from_account_id,
        'toAccountId': to_account_id,
        'scheduledDate': date,
        'amount': amount
    })
    if r.text == '':
        return None
    else:
        return r.json()


def getActivities(bank_account_id):
    r = requests.get(url = BACK_END_ENDPOINT + '/activities/list', params={
        'bankAccountId': bank_account_id})
    result = dict()
    if r.text == '':
        return None
    else:
        return r.json()

# print getActivities()



# print setUpAccount(8)
