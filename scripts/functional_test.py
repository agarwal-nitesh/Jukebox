import http.client

conn = http.client.HTTPConnection("localhost:8080")
headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "ad0746d1-6c3b-85c5-98da-549a98a538e5"
    }


# ------------------------------- CREATE ARTISTS -------------------------------------

payload = "{\n\t\"name\": \"The Beatles\",\n\t\"rating\": 4.9\n}"
conn.request("POST", "/artist", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))

payload = "{\n\t\"name\": \"One Republic\",\n\t\"rating\": 4.2\n}"
conn.request("POST", "/artist", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))


payload = "{\n\t\"name\": \"Queen\",\n\t\"rating\": 4.8\n}"
conn.request("POST", "/artist", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))


payload = "{\n\t\"name\": \"Pink Floyd\",\n\t\"rating\": 4.8\n}"
conn.request("POST", "/artist", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))

payload = "{\n\t\"name\": \"The Doors\",\n\t\"rating\": 4.7\n}"
conn.request("POST", "/artist", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))



payload = "{\n\t\"name\": \"Slash\",\n\t\"rating\": 5\n}"
conn.request("POST", "/artist", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))


payload = "{\n\t\"name\": \"Avicii\",\n\t\"rating\": 4.4\n}"
conn.request("POST", "/artist", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))

payload = "{\n\t\"name\": \"Maroon\",\n\t\"rating\": 4\n}"
conn.request("POST", "/artist", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))


payload = "{\n\t\"name\": \"Ed Shereen\",\n\t\"rating\": 4.1\n}"
conn.request("POST", "/artist", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))



# ---------------------------- CREATE SONGS ------------------------------------------

payload = "{\n\t\"songName\": \"Counting Stars\",\n\t\"artistName\": \"One Republic\",\n\t\"url\": \"https://www.youtube.com/watch?v=IcrbM1l_BoI\",\n\t\"rating\": 4.5\n}"

headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "8daffcb8-0cb9-406d-4a1c-1170839841db"
    }

conn.request("POST", "/song", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))




payload = "{\n\t\"songName\": \"Secrets\",\n\t\"artistName\": \"One Republic\",\n\t\"url\": \"https://www.youtube.com/watch?v=IcrbM1l_BoI\",\n\t\"rating\": 4.3\n}"

headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "8daffcb8-0cb9-406d-4a1c-1170839841db"
    }

conn.request("POST", "/song", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))



payload = "{\n\t\"songName\": \"Wake me up\",\n\t\"artistName\": \"Avicii\",\n\t\"url\": \"https://www.youtube.com/watch?v=IcrbM1l_BoI\",\n\t\"rating\": 4.5\n}"

headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "8daffcb8-0cb9-406d-4a1c-1170839841db"
    }

conn.request("POST", "/song", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))



payload = "{\n\t\"songName\": \"Girls like you\",\n\t\"artistName\": \"Maroon\",\n\t\"url\": \"https://www.youtube.com/watch?v=IcrbM1l_BoI\",\n\t\"rating\": 4.5\n}"

headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "8daffcb8-0cb9-406d-4a1c-1170839841db"
    }

conn.request("POST", "/song", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))



payload = "{\n\t\"songName\": \"November Rain\",\n\t\"artistName\": \"Slash\",\n\t\"url\": \"https://www.youtube.com/watch?v=IcrbM1l_BoI\",\n\t\"rating\": 4.9\n}"

headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "8daffcb8-0cb9-406d-4a1c-1170839841db"
    }

conn.request("POST", "/song", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))



payload = "{\n\t\"songName\": \"Comfortably Numb\",\n\t\"artistName\": \"Pink Floyd\",\n\t\"url\": \"https://www.youtube.com/watch?v=IcrbM1l_BoI\",\n\t\"rating\": 4.8\n}"

headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "8daffcb8-0cb9-406d-4a1c-1170839841db"
    }

conn.request("POST", "/song", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))



payload = "{\n\t\"songName\": \"Money\",\n\t\"artistName\": \"Pink Floyd\",\n\t\"url\": \"https://www.youtube.com/watch?v=IcrbM1l_BoI\",\n\t\"rating\": 4.6\n}"

headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "8daffcb8-0cb9-406d-4a1c-1170839841db"
    }

conn.request("POST", "/song", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))



payload = "{\n\t\"songName\": \"Bohemian Rhapsody\",\n\t\"artistName\": \"Queen\",\n\t\"url\": \"https://www.youtube.com/watch?v=IcrbM1l_BoI\",\n\t\"rating\": 5\n}"

headers = {
    'content-type': "application/json",
    'cache-control': "no-cache",
    'postman-token': "8daffcb8-0cb9-406d-4a1c-1170839841db"
    }

conn.request("POST", "/song", payload, headers)

res = conn.getresponse()
data = res.read()

print(data.decode("utf-8"))


# --------------------------------------- GET SONG BY NAME -------------------------------------

for i in range(0, 100):
    conn.request("GET", "/song?name=Bohemian%20Rhapsody", headers=headers)

    res = conn.getresponse()
    data = res.read()

    print(data.decode("utf-8"))


for i in range(0,50)
    conn.request("GET", "/song?name=November%20Rain", headers=headers)

    res = conn.getresponse()
    data = res.read()

    print(data.decode("utf-8"))

for i in range(0,30)
    conn.request("GET", "/song?name=Counting%20Stars", headers=headers)

    res = conn.getresponse()
    data = res.read()

    print(data.decode("utf-8"))

    conn.request("GET", "/song?name=Money", headers=headers)

    res = conn.getresponse()
    data = res.read()

    conn.request("GET", "/song?name=Comfortably%20Numb", headers=headers)

    res = conn.getresponse()
    data = res.read()
