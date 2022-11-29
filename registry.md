<!-- Output copied to clipboard! -->

<!-----

Yay, no errors, warnings, or alerts!

Conversion time: 1.191 seconds.


Using this Markdown file:

1. Paste this output into your source file.
2. See the notes and action items below regarding this conversion run.
3. Check the rendered output (headings, lists, code blocks, tables) for proper
   formatting and use a linkchecker before you publish this page.

Conversion notes:

* Docs to Markdown version 1.0β33
* Tue Nov 29 2022 09:57:44 GMT-0800 (PST)
* Source doc: Registry-Cert-Update
----->


**Registry-Server:**

[admin@registry newcrt]$ ls



1. **Key Creation**

[admin@registry newcrt]$ openssl genrsa -out ca.key 2048

Generating RSA private key, 2048 bit long modulus (2 primes)

......................................................................+++++

.................................................................+++++

e is 65537 (0x010001)

[admin@registry newcrt]$ ls -lstr

total 4

4 -rw------- 1 admin admin 1679 Nov 29 15:52 ca.key



2. **Ca.crt creation**

[admin@registry newcrt]$ openssl req -new -x509 -days 5000 -key ca.key -subj "/C=IN/ST=MUMBAI/L=NAVI MUMBAI/O=BANK/CN=finopaymentbank.in" -out ca.crt

[admin@registry newcrt]$ ls -lstr

total 8

4 -rw------- 1 admin admin 1679 Nov 29 15:52 ca.key

4 -rw-rw-r-- 1 admin admin 1318 Nov 29 15:52 ca.crt



3. **Server.csr Creation**

[admin@registry newcrt]$ openssl req -newkey rsa:2048 -nodes -keyout server.key -subj "/C=IN/ST=MUMBAI/L=NAVI MUMBAI/O=BANK/CN=*.finopaymentbank.in" -out server.csr

Generating a RSA private key

......................................................+++++

.........................................................................................+++++

writing new private key to 'server.key'

-----

[admin@registry newcrt]$ ls -lstr

total 16

4 -rw------- 1 admin admin 1679 Nov 29 15:52 ca.key

4 -rw-rw-r-- 1 admin admin 1318 Nov 29 15:52 ca.crt

4 -rw------- 1 admin admin 1704 Nov 29 15:53 server.key

4 -rw-rw-r-- 1 admin admin  997 Nov 29 15:53 server.csr



4. **Server.crt Creation**

[admin@registry newcrt]$ openssl x509 -req -extfile &lt;(printf "subjectAltName=DNS:finopaymentbank.in,DNS:registry.finopaymentbank.in") -days 5000 -in server.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out server.crt

Signature ok

subject=C = IN, ST = MUMBAI, L = NAVI MUMBAI, O = BANK, CN = *.finopaymentbank.in

Getting CA Private Key

[admin@registry newcrt]$ ls -lstr

total 24

4 -rw------- 1 admin admin 1679 Nov 29 15:52 ca.key

4 -rw-rw-r-- 1 admin admin 1318 Nov 29 15:52 ca.crt

4 -rw------- 1 admin admin 1704 Nov 29 15:53 server.key

4 -rw-rw-r-- 1 admin admin  997 Nov 29 15:53 server.csr

4 -rw-rw-r-- 1 admin admin   41 Nov 29 15:54 ca.srl

4 -rw-rw-r-- 1 admin admin 1294 Nov 29 15:54 server.crt

[admin@registry newcrt]$ ls -lstr ../crt/

total 24

4 -rw------- 1 admin admin 1679 Nov 28  2021 ca.key

4 -rw-rw-r-- 1 admin admin 1318 Nov 28  2021 ca.crt

4 -rw------- 1 admin admin 1708 Nov 28  2021 server.key

4 -rw-rw-r-- 1 admin admin  997 Nov 28  2021 server.csr

4 -rw-rw-r-- 1 admin admin   41 Nov 28  2021 ca.srl

4 -rw-rw-r-- 1 admin admin 1294 Nov 28  2021 server.crt



5. **Verfing server.crt**

[admin@registry newcrt]$ openssl x509 -in server.crt -text -noout

Certificate:

    Data:

        Version: 3 (0x2)

        Serial Number:

            55:ec:3e:2b:af:6f:47:65:1f:5b:9b:30:bb:85:30:db:a5:a3:3b:c6

        Signature Algorithm: sha256WithRSAEncryption

        Issuer: C = IN, ST = MUMBAI, L = NAVI MUMBAI, O = BANK, CN = finopaymentbank.in

        Validity

            Not Before: Nov 29 10:24:24 2022 GMT

            Not After : Aug  7 10:24:24 2036 GMT

        Subject: C = IN, ST = MUMBAI, L = NAVI MUMBAI, O = BANK, CN = *.finopaymentbank.in

        Subject Public Key Info:

            Public Key Algorithm: rsaEncryption

                RSA Public-Key: (2048 bit)

                Modulus:

                    00:9a:77:b5:6d:06:24:5e:a6:47:52:ab:a8:59:c3:

                    31:8e:7f:f2:3b:2e:f9:f0:28:d6:80:78:0d:9b:b7:

                    00:34:ef:e5:de:2b:27:f9:e0:5b:54:c1:bf:21:6e:

                    f4:bd:d5:88:fd:94:51:0b:a5:c8:55:62:e9:9c:57:

                    1e:c0:6f:19:db:8e:06:34:04:f3:ed:7b:a6:2e:a5:

                    fb:83:64:3f:6c:b5:2a:be:46:64:a4:90:91:de:28:

                    8b:70:45:73:ad:b8:79:cf:08:a0:c9:e8:a5:4f:c7:

                    9c:05:7a:b7:d0:de:51:ef:a6:a4:88:4f:a2:0b:78:

                    50:f8:72:97:b9:6f:51:02:b9:f1:d7:96:37:5b:40:

                    b0:34:fe:25:6d:5e:9c:95:3f:40:4b:2c:26:4e:b7:

                    55:89:ea:22:bb:bf:99:7f:2a:6c:e1:94:c3:09:74:

                    0f:57:f9:e6:bb:9c:00:32:6f:98:29:6d:1e:72:68:

                    fa:b6:dd:59:eb:23:d1:09:40:3b:06:54:35:4a:6c:

                    27:d5:54:16:b9:5b:a4:f3:96:f1:b8:77:b4:58:7e:

                    51:69:c0:a0:d1:73:cd:ef:97:b5:c6:97:86:8f:38:

                    18:bc:59:a0:25:80:58:68:7e:57:f7:c3:3e:3e:b5:

                    5c:0e:e6:a1:9c:20:2e:4a:68:c2:1c:7a:b3:8c:a9:

                    e5:7d

                Exponent: 65537 (0x10001)

        X509v3 extensions:

            X509v3 Subject Alternative Name: 

                DNS:finopaymentbank.in, DNS:registry.finopaymentbank.in

    Signature Algorithm: sha256WithRSAEncryption

         aa:30:c5:9b:cd:9c:ee:4d:db:6c:18:40:91:81:f7:c9:a0:65:

         96:f1:97:fc:ca:f6:33:b3:76:f5:15:56:60:5d:5b:d4:d1:ef:

         3d:18:0c:1b:c3:67:6c:1a:b5:29:a0:79:ff:42:b4:b4:97:46:

         c7:1d:88:bb:b3:50:a8:3e:b5:69:0e:fe:c1:ea:95:be:ef:fc:

         01:cc:c2:a6:65:a0:75:59:c1:35:3f:07:2e:d3:ab:cc:33:25:

         fc:7a:00:af:62:1f:cd:70:8d:eb:2a:c6:9e:b0:9c:e6:b1:f3:

         36:c2:31:1b:9d:8f:4a:24:24:a0:45:72:63:36:36:fe:6f:b5:

         69:13:97:2e:b6:7f:20:72:c6:35:07:29:76:12:1a:ce:12:08:

         a8:33:8b:f6:77:99:f5:07:a5:1f:62:b4:dc:fe:0b:fe:a7:ca:

         32:d6:f3:65:2c:9b:33:8b:cf:fd:0f:f8:9a:fa:b1:de:e1:76:

         51:be:27:de:10:05:1e:00:f1:51:8d:60:90:ea:6e:3b:4c:5c:

         d2:56:e7:13:30:f9:a3:8e:39:e3:dc:b4:81:92:00:68:6f:7a:

         96:9e:18:7c:35:6c:7a:6b:4b:7e:db:11:d5:30:f0:19:83:b3:

         13:a4:34:55:fe:55:5e:4c:ba:95:b5:aa:62:41:86:39:81:56:

         6b:de:36:36

[admin@registry newcrt]$ ls -lstr

total 24

4 -rw------- 1 admin admin 1679 Nov 29 15:52 ca.key

4 -rw-rw-r-- 1 admin admin 1318 Nov 29 15:52 ca.crt

4 -rw------- 1 admin admin 1704 Nov 29 15:53 server.key

4 -rw-rw-r-- 1 admin admin  997 Nov 29 15:53 server.csr

4 -rw-rw-r-- 1 admin admin   41 Nov 29 15:54 ca.srl

4 -rw-rw-r-- 1 admin admin 1294 Nov 29 15:54 server.crt



6. **Copy crt and serverkey into /etc/ssl/f**

[admin@registry newcrt]$ sudo cp server.crt /etc/ssl/finobank.crt

[admin@registry newcrt]$ sudo cp server.key /etc/ssl/finobank.key

[admin@registry newcrt]$ ls -lstr

total 24

4 -rw------- 1 admin admin 1679 Nov 29 15:52 ca.key

4 -rw-rw-r-- 1 admin admin 1318 Nov 29 15:52 ca.crt

4 -rw------- 1 admin admin 1704 Nov 29 15:53 server.key

4 -rw-rw-r-- 1 admin admin  997 Nov 29 15:53 server.csr

4 -rw-rw-r-- 1 admin admin   41 Nov 29 15:54 ca.srl

4 -rw-rw-r-- 1 admin admin 1294 Nov 29 15:54 server.crt

[admin@registry newcrt]$ ls -lstr /etc/ssl/finobank.crt

4 -rw-r--r-- 1 root root 1294 Nov 29 15:56 /etc/ssl/finobank.crt

[admin@registry newcrt]$ ls -lstr /etc/ssl/finobank.key

4 -rw------- 1 root root 1704 Nov 29 15:56 /etc/ssl/finobank.key

[admin@registry newcrt]$ sudo cp ca.key /etc/pki/ca-trust/source/anchors/

[admin@registry newcrt]$ ls -sltr /etc/pki/ca-trust/source/anchors/ca.key 

4 -rw------- 1 root root 1679 Nov 29 15:57 /etc/pki/ca-trust/source/anchors/ca.key

[admin@registry newcrt]$ sudo rm -rf /etc/pki/ca-trust/source/anchors/ca.key

[admin@registry newcrt]$ ls -sltr /etc/pki/ca-trust/source/anchors/ca.key 

ls: cannot access '/etc/pki/ca-trust/source/anchors/ca.key': No such file or directory



7. **Copy ca.crt /etc/pki/ca-trust/source/anchors/ and update it **

[admin@registry newcrt]$ sudo cp ca.crt /etc/pki/ca-trust/source/anchors/

[admin@registry newcrt]$ ls -sltr /etc/pki/ca-trust/source/anchors/ca.crt 

4 -rw-r--r-- 1 root root 1318 Nov 29 15:58 /etc/pki/ca-trust/source/anchors/ca.crt

[admin@registry newcrt]$ sudo update-ca-trust 



8. **Restart ngnix**

[admin@registry newcrt]$ sudo systemctl restart nginx

[admin@registry newcrt]$ sudo systemctl status nginx

● nginx.service - The nginx HTTP and reverse proxy server

   Loaded: loaded (/usr/lib/systemd/system/nginx.service; enabled; vendor preset: disabled)

   Active: active (running) since Tue 2022-11-29 16:00:03 IST; 6s ago

  Process: 1897587 ExecStart=/usr/sbin/nginx (code=exited, status=0/SUCCESS)

  Process: 1897585 ExecStartPre=/usr/sbin/nginx -t (code=exited, status=0/SUCCESS)

  Process: 1897582 ExecStartPre=/usr/bin/rm -f /run/nginx.pid (code=exited, status=0/SUCCESS)

 Main PID: 1897588 (nginx)

    Tasks: 9 (limit: 204176)

   Memory: 13.3M

   CGroup: /system.slice/nginx.service

           ├─1897588 nginx: master process /usr/sbin/nginx

           ├─1897589 nginx: worker process

           ├─1897590 nginx: worker process

           ├─1897591 nginx: worker process

           ├─1897592 nginx: worker process

           ├─1897593 nginx: worker process

           ├─1897594 nginx: worker process

           ├─1897595 nginx: worker process

           └─1897596 nginx: worker process

Nov 29 16:00:03 registry.finopaymentbank.in systemd[1]: Starting The nginx HTTP and reverse proxy server...

Nov 29 16:00:03 registry.finopaymentbank.in nginx[1897585]: nginx: the configuration file /etc/nginx/nginx.conf syntax is ok

Nov 29 16:00:03 registry.finopaymentbank.in nginx[1897585]: nginx: configuration file /etc/nginx/nginx.conf test is successful

Nov 29 16:00:03 registry.finopaymentbank.in systemd[1]: Started The nginx HTTP and reverse proxy server.

[admin@registry newcrt]$ 

**Openshift:**

-----

[ocp@bastain Deployment]$ mkdir rgiscrt

[ocp@bastain Deployment]$ cd rgiscrt/

[ocp@bastain rgiscrt]$ ls



9. **Copy ca.crt in openshift**

[ocp@bastain rgiscrt]$ vim ca.crt

[ocp@bastain rgiscrt]$ openssl x509 -in ca.crt -text -noout

Certificate:

    Data:

        Version: 3 (0x2)

        Serial Number:

            28:54:b8:8d:ef:1f:55:e5:0d:80:5a:5b:de:5c:5d:70:54:88:e2:a1

        Signature Algorithm: sha256WithRSAEncryption

        Issuer: C = IN, ST = MUMBAI, L = NAVI MUMBAI, O = BANK, CN = finopaymentbank.in

        Validity

            Not Before: Nov 29 10:22:54 2022 GMT

            Not After : Aug  7 10:22:54 2036 GMT

        Subject: C = IN, ST = MUMBAI, L = NAVI MUMBAI, O = BANK, CN = finopaymentbank.in

        Subject Public Key Info:

            Public Key Algorithm: rsaEncryption

                RSA Public-Key: (2048 bit)

                Modulus:

                    00:e4:65:71:6d:14:bb:67:e3:44:8f:50:ce:cc:46:

                    9f:fb:ea:2f:78:06:7d:39:7c:51:13:ba:b5:85:d3:

                    4a:5a:20:0b:a4:0e:71:ff:74:d5:5b:4b:1e:0c:40:

                    90:bc:56:ca:6e:94:82:b8:9d:ff:54:5d:a0:a7:1e:

                    87:91:ef:a4:13:61:bc:23:4b:1d:83:10:ad:29:f1:

                    11:eb:d2:aa:66:76:a7:f2:8d:e5:f5:92:4f:b1:3f:

                    8e:2f:f8:d2:3e:96:0d:10:e3:1e:a1:7c:92:95:ff:

                    62:c0:4e:ac:e8:8d:8b:8b:36:d5:c5:6e:bd:44:18:

                    29:d5:37:8f:1d:3f:6f:d6:9c:0e:f2:9c:51:97:12:

                    54:77:7b:3a:90:ec:ac:dc:ec:49:c0:3a:1a:39:ae:

                    74:81:10:ea:a0:3c:85:d3:cb:47:90:3b:47:0d:a2:

                    3e:3d:36:55:17:7d:4c:22:e4:70:41:d7:77:8c:15:

                    90:ad:60:9b:a0:70:3a:e1:36:ea:7c:f2:a2:b6:37:

                    43:9b:28:aa:c9:a2:f6:b3:d4:d0:e4:71:d0:f5:c3:

                    7e:98:0c:d6:ad:f0:87:58:d1:d0:f0:b4:78:ab:b3:

                    b7:ba:8d:cb:78:55:97:ae:d8:5a:79:63:f6:54:70:

                    5d:c5:0c:a9:33:0f:1e:01:04:cb:4b:6e:d2:60:94:

                    14:6d

                Exponent: 65537 (0x10001)

        X509v3 extensions:

            X509v3 Subject Key Identifier: 

                DF:94:8D:6A:A2:86:B9:7E:9A:60:85:73:92:4D:DA:65:FE:53:6E:9C

            X509v3 Authority Key Identifier: 

                keyid:DF:94:8D:6A:A2:86:B9:7E:9A:60:85:73:92:4D:DA:65:FE:53:6E:9C

            X509v3 Basic Constraints: critical

                CA:TRUE

    Signature Algorithm: sha256WithRSAEncryption

         d3:05:a4:53:15:c8:7f:8c:b0:52:46:d0:81:1d:ad:eb:4e:59:

         02:c8:17:89:e5:ce:15:ea:3d:b2:75:4d:cf:e8:32:98:ee:df:

         a2:67:bc:46:e9:8a:79:29:60:f1:02:26:fa:f5:0f:8c:ed:70:

         59:2a:93:8d:38:6e:f7:fa:78:0d:38:ce:df:68:0c:f5:85:6c:

         26:8c:e1:40:15:8f:af:ef:ec:09:c5:65:86:83:cb:18:2d:bf:

         37:6a:3e:98:70:cb:04:2e:17:56:6b:f7:15:8a:55:e9:a4:b4:

         50:df:09:49:85:6c:27:63:61:58:3f:63:15:6b:64:60:13:b5:

         49:f5:46:f3:c5:8f:a1:6f:36:1b:b4:b5:20:53:40:6a:31:8f:

         ed:56:b6:fe:fb:21:e7:9d:21:5e:38:11:f2:85:4b:74:75:1f:

         53:04:2b:3e:9f:6d:b9:d9:ca:52:50:5c:52:bf:c1:d8:27:ff:

         1f:81:73:2c:7e:61:f8:55:b0:47:c3:27:8c:d8:b3:ef:f0:02:

         9c:f7:58:18:8e:3a:10:5f:4a:e6:30:d2:5f:a0:e5:10:bf:80:

         99:af:78:26:d5:62:e0:2e:5f:24:23:e2:2b:3a:f1:e9:5d:0b:

         be:d5:bb:6e:cb:4e:56:ec:3f:ac:5a:d7:ff:19:d3:77:37:c1:

         5c:69:bb:3a

[ocp@bastain rgiscrt]$ ls

ca.crt

[ocp@bastain rgiscrt]$ pwd

/home/ocp/Deployment/rgiscrt

[ocp@bastain rgiscrt]$ oc get cm -n openshift-config 

NAME                               DATA   AGE

admin-acks                         0      374d

admin-kubeconfig-client-ca         1      374d

cloud-provider-config              1      374d

etcd-ca-bundle                     1      374d

etcd-metric-serving-ca             1      374d

etcd-serving-ca                    1      374d

initial-kube-apiserver-server-ca   1      374d

kube-root-ca.crt                   1      374d

openshift-install                  2      374d

openshift-install-manifests        2      374d

openshift-service-ca.crt           1      374d

registry-cas                       1      366d

[ocp@bastain rgiscrt]$ oc get route^C

[ocp@bastain rgiscrt]$ #oc create configmap registry-cas-new -n openshift-config --from-file=registry.finopaymentbank.in..8582=/home/ocp/Deployment/rgiscrt/ca.crt

[ocp@bastain rgiscrt]$ oc get cm registry-cas -o yaml -n openshift-config

apiVersion: v1

data:

  registry.finopaymentbank.in: |

    -----BEGIN CERTIFICATE-----

    MIIDoTCCAomgAwIBAgIUK0X394mA5S6WnNxnCZUxPqx6LCcwDQYJKoZIhvcNAQEL

    BQAwYDELMAkGA1UEBhMCSU4xDzANBgNVBAgMBk1VTUJBSTEUMBIGA1UEBwwLTkFW

    SSBNVU1CQUkxDTALBgNVBAoMBEJBTksxGzAZBgNVBAMMEmZpbm9wYXltZW50YmFu

    ay5pbjAeFw0yMTExMjgwODM5NTdaFw0yMjExMjgwODM5NTdaMGAxCzAJBgNVBAYT

    AklOMQ8wDQYDVQQIDAZNVU1CQUkxFDASBgNVBAcMC05BVkkgTVVNQkFJMQ0wCwYD

    VQQKDARCQU5LMRswGQYDVQQDDBJmaW5vcGF5bWVudGJhbmsuaW4wggEiMA0GCSqG

    SIb3DQEBAQUAA4IBDwAwggEKAoIBAQDEKUSsnTWsr/QMEZgNs9wk1xjEshusy+Hi

    Y4gmaGz2x3mDUsallDypPql0PJGAbPGCii6PQ4wYclT83TS+QuQNUPbVlVlnozm5

    f+PDXZzh3bfUhoBB/WKj9EUQt4UnSrQiTmVOaYmzkbjYgAynx4zYLpUNDjQK2fmU

    UlgZxmY4DQF5WzuAea0gBlJMhUGoc8Ovnt0GvoSKpT4TeoganvWii72BgHpBhIeE

    SHxkNwLrnXCLDU/Ysci00wbshhRkHNiABbvy1V6qQw6qvSJmVUn+j/7vFE2mP/MX

    iaIPTjQVNj4hvW2N/sZ3hMPstZYZHZzj96OL/BPGVq/KYV5lazx9AgMBAAGjUzBR

    MB0GA1UdDgQWBBQhqERQY2r5T8KH2fYqZbQFg783sjAfBgNVHSMEGDAWgBQhqERQ

    Y2r5T8KH2fYqZbQFg783sjAPBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUA

    A4IBAQBeesUXncocYvrAY3S1NFjkVrX8XCKjfSDa2zSOqbXh2oHRLN4D7+0pzOy0

    ED2tssi2M1m2P23ZAPuKMNO2EUinaWz43PCbMrEvqCibDXGsLnqxGEmfIUR2I91N

    e/ZeloBcnbtQBGkEu/wGACSdjsnWAxs5CgbHfWk4eGeZK70tcuIK741dKs280bkq

    GSUoTsw7dMN/GIyoYQESprxpadR6q6+523Nowx601rwtBhvKEBNxmzpWBZK/SVOj

    rs9yThKdenHYl5JiHPkPJBI0S73R3L8AxQ+tdLq/ldyGCIFeuOeNnZs46nz39/TE

    x3yU6NZUmYH+meAADQZqt2trNV+R

    -----END CERTIFICATE-----

kind: ConfigMap

metadata:

  creationTimestamp: "2021-11-28T10:43:03Z"

  name: registry-cas

  namespace: openshift-config

  resourceVersion: "3828163"

  uid: 585754c1-1a9d-4051-ab33-ac60a8dc7596

[ocp@bastain rgiscrt]$ oc create configmap registry-cas-new -n openshift-config --from-file=registry.finopaymentbank.in..8582=/home/ocp/Deployment/rgiscrt/ca.crt

configmap/registry-cas-new created

[ocp@bastain rgiscrt]$ oc get cm -n openshift-config 

NAME                               DATA   AGE

admin-acks                         0      374d

admin-kubeconfig-client-ca         1      374d

cloud-provider-config              1      374d

etcd-ca-bundle                     1      374d

etcd-metric-serving-ca             1      374d

etcd-serving-ca                    1      374d

initial-kube-apiserver-server-ca   1      374d

kube-root-ca.crt                   1      374d

openshift-install                  2      374d

openshift-install-manifests        2      374d

openshift-service-ca.crt           1      374d

registry-cas                       1      366d

registry-cas-new                   1      7s

[ocp@bastain rgiscrt]$ oc get cm registry-cas-new -o yaml -n openshift-config

apiVersion: v1

data:

  registry.finopaymentbank.in..8582: |+

    -----BEGIN CERTIFICATE-----

    MIIDoTCCAomgAwIBAgIUKFS4je8fVeUNgFpb3lxdcFSI4qEwDQYJKoZIhvcNAQEL

    BQAwYDELMAkGA1UEBhMCSU4xDzANBgNVBAgMBk1VTUJBSTEUMBIGA1UEBwwLTkFW

    SSBNVU1CQUkxDTALBgNVBAoMBEJBTksxGzAZBgNVBAMMEmZpbm9wYXltZW50YmFu

    ay5pbjAeFw0yMjExMjkxMDIyNTRaFw0zNjA4MDcxMDIyNTRaMGAxCzAJBgNVBAYT

    AklOMQ8wDQYDVQQIDAZNVU1CQUkxFDASBgNVBAcMC05BVkkgTVVNQkFJMQ0wCwYD

    VQQKDARCQU5LMRswGQYDVQQDDBJmaW5vcGF5bWVudGJhbmsuaW4wggEiMA0GCSqG

    SIb3DQEBAQUAA4IBDwAwggEKAoIBAQDkZXFtFLtn40SPUM7MRp/76i94Bn05fFET

    urWF00paIAukDnH/dNVbSx4MQJC8VspulIK4nf9UXaCnHoeR76QTYbwjSx2DEK0p

    8RHr0qpmdqfyjeX1kk+xP44v+NI+lg0Q4x6hfJKV/2LATqzojYuLNtXFbr1EGCnV

    N48dP2/WnA7ynFGXElR3ezqQ7Kzc7EnAOho5rnSBEOqgPIXTy0eQO0cNoj49NlUX

    fUwi5HBB13eMFZCtYJugcDrhNup88qK2N0ObKKrJovaz1NDkcdD1w36YDNat8IdY

    0dDwtHirs7e6jct4VZeu2Fp5Y/ZUcF3FDKkzDx4BBMtLbtJglBRtAgMBAAGjUzBR

    MB0GA1UdDgQWBBTflI1qooa5fppghXOSTdpl/lNunDAfBgNVHSMEGDAWgBTflI1q

    ooa5fppghXOSTdpl/lNunDAPBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUA

    A4IBAQDTBaRTFch/jLBSRtCBHa3rTlkCyBeJ5c4V6j2ydU3P6DKY7t+iZ7xG6Yp5

    KWDxAib69Q+M7XBZKpONOG73+ngNOM7faAz1hWwmjOFAFY+v7+wJxWWGg8sYLb83

    aj6YcMsELhdWa/cVilXppLRQ3wlJhWwnY2FYP2MVa2RgE7VJ9UbzxY+hbzYbtLUg

    U0BqMY/tVrb++yHnnSFeOBHyhUt0dR9TBCs+n2252cpSUFxSv8HYJ/8fgXMsfmH4

    VbBHwyeM2LPv8AKc91gYjjoQX0rmMNJfoOUQv4CZr3gm1WLgLl8kI+IrOvHpXQu+

    1btuy05W7D+sWtf/GdN3N8Fcabs6

    -----END CERTIFICATE-----

kind: ConfigMap

metadata:

  creationTimestamp: "2022-11-29T11:00:39Z"

  name: registry-cas-new

  namespace: openshift-config

  resourceVersion: "280335449"

  uid: 0d1e2e9e-9911-4316-b6f6-541ca49ea005



10. **Removing Port**

[ocp@bastain rgiscrt]$ oc delete cm registry-cas-new -n openshift-config

configmap "registry-cas-new" deleted

[ocp@bastain rgiscrt]$ oc create configmap registry-cas-new -n openshift-config --from-file=registry.finopaymentbank.in=/home/ocp/Deployment/rgiscrt/ca.crt

configmap/registry-cas-new created

[ocp@bastain rgiscrt]$ oc get cm -n openshift-config 

NAME                               DATA   AGE

admin-acks                         0      374d

admin-kubeconfig-client-ca         1      374d

cloud-provider-config              1      374d

etcd-ca-bundle                     1      374d

etcd-metric-serving-ca             1      374d

etcd-serving-ca                    1      374d

initial-kube-apiserver-server-ca   1      374d

kube-root-ca.crt                   1      374d

openshift-install                  2      374d

openshift-install-manifests        2      374d

openshift-service-ca.crt           1      374d

registry-cas                       1      366d

registry-cas-new                   1      3s

[ocp@bastain rgiscrt]$ oc get cm registry-cas-new -o yaml -n openshift-config

apiVersion: v1

data:

  registry.finopaymentbank.in: |+

    -----BEGIN CERTIFICATE-----

    MIIDoTCCAomgAwIBAgIUKFS4je8fVeUNgFpb3lxdcFSI4qEwDQYJKoZIhvcNAQEL

    BQAwYDELMAkGA1UEBhMCSU4xDzANBgNVBAgMBk1VTUJBSTEUMBIGA1UEBwwLTkFW

    SSBNVU1CQUkxDTALBgNVBAoMBEJBTksxGzAZBgNVBAMMEmZpbm9wYXltZW50YmFu

    ay5pbjAeFw0yMjExMjkxMDIyNTRaFw0zNjA4MDcxMDIyNTRaMGAxCzAJBgNVBAYT

    AklOMQ8wDQYDVQQIDAZNVU1CQUkxFDASBgNVBAcMC05BVkkgTVVNQkFJMQ0wCwYD

    VQQKDARCQU5LMRswGQYDVQQDDBJmaW5vcGF5bWVudGJhbmsuaW4wggEiMA0GCSqG

    SIb3DQEBAQUAA4IBDwAwggEKAoIBAQDkZXFtFLtn40SPUM7MRp/76i94Bn05fFET

    urWF00paIAukDnH/dNVbSx4MQJC8VspulIK4nf9UXaCnHoeR76QTYbwjSx2DEK0p

    8RHr0qpmdqfyjeX1kk+xP44v+NI+lg0Q4x6hfJKV/2LATqzojYuLNtXFbr1EGCnV

    N48dP2/WnA7ynFGXElR3ezqQ7Kzc7EnAOho5rnSBEOqgPIXTy0eQO0cNoj49NlUX

    fUwi5HBB13eMFZCtYJugcDrhNup88qK2N0ObKKrJovaz1NDkcdD1w36YDNat8IdY

    0dDwtHirs7e6jct4VZeu2Fp5Y/ZUcF3FDKkzDx4BBMtLbtJglBRtAgMBAAGjUzBR

    MB0GA1UdDgQWBBTflI1qooa5fppghXOSTdpl/lNunDAfBgNVHSMEGDAWgBTflI1q

    ooa5fppghXOSTdpl/lNunDAPBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUA

    A4IBAQDTBaRTFch/jLBSRtCBHa3rTlkCyBeJ5c4V6j2ydU3P6DKY7t+iZ7xG6Yp5

    KWDxAib69Q+M7XBZKpONOG73+ngNOM7faAz1hWwmjOFAFY+v7+wJxWWGg8sYLb83

    aj6YcMsELhdWa/cVilXppLRQ3wlJhWwnY2FYP2MVa2RgE7VJ9UbzxY+hbzYbtLUg

    U0BqMY/tVrb++yHnnSFeOBHyhUt0dR9TBCs+n2252cpSUFxSv8HYJ/8fgXMsfmH4

    VbBHwyeM2LPv8AKc91gYjjoQX0rmMNJfoOUQv4CZr3gm1WLgLl8kI+IrOvHpXQu+

    1btuy05W7D+sWtf/GdN3N8Fcabs6

    -----END CERTIFICATE-----

kind: ConfigMap

metadata:

  creationTimestamp: "2022-11-29T11:01:39Z"

  name: registry-cas-new

  namespace: openshift-config

  resourceVersion: "280336129"

  uid: 408f39e4-11e1-48cf-b047-089278a503c4

[ocp@bastain rgiscrt]$ oc get cm registry-cas-new -o yaml -n openshift-config^C

[ocp@bastain rgiscrt]$ oc patch image.config.openshift.io/cluster --patch '{"spec":{"additionalTrustedCA":{"name":"registry-cas-new"}}}' --type=merge

image.config.openshift.io/cluster patched

===

