import requests
import datetime

BACK_END_ENDPOINT = 'http://128.237.210.65:8002'
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
