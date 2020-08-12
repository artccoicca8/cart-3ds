def request(url,pemfile,password, operatorID, refNumber):
    context = ssl.SSLContext(ssl.PROTOCOL_TLSv1_2)
    print("Password PEM : "+ password)
    context.load_cert_chain(certfile="{}.pem".format(pemfile), password=password)
    request_headers = {
        'Content-Type': 'application/json;charset=UTF-8'
    }
    try:
        url1 = re.search('https://(.+?)/', "{}//".format(url)).group(1)
        url2 = re.search('{}(.+?)//'.format(url1), "{}//".format(url)).group(1)
    except AttributeError:
        url1 = ''
        url2 = ''

    uid = str(uuid.uuid1())
    body = {
        "threeDSServerRefNumber":refNumber,
        "threeDSServerOperatorID":operatorID,
        "threeDSServerTransID":uid,
        "messageType":"PReq",
        "messageVersion":"2.1.0"
    }
    connection = http.client.HTTPSConnection(url1, port=443, context=context)
    connection.request(method="POST", url=url2, headers=request_headers, body=json.dumps(body))
    response = connection.getresponse()
    result = {"body":response.read().decode(), "statusCode": response.status}
    print("RESULT"+ json.dumps(result))
    connection.close()
    
    return result