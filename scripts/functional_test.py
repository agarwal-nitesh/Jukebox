import http.client

conn = http.client.HTTPConnection("localhost:8080")




# ---------------------------- CREATE SONGS ------------------------------------------

payload = "{\n\t\"name\": \"Lost\",\n\t\"artist_name\": \"Avici\",\n\t\"url\": \"http://test.com/play/123\",\n\t\"rating\": 5\n}"

headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "8f113830-1a59-1ff0-7b33-cec0a65602c1"
    }

conn.request("POST", "/song", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))