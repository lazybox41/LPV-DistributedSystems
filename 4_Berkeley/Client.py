import datetime
import threading
import time
from dateutil import parser
import socket


def sendTime(slave):
    while True:
        slave.send(str(datetime.datetime.now()).encode())
        print("Sent time")
        time.sleep(5)


def receiveTime(slave):
    while True:
        syncedTime = parser.parse(slave.recv(1024).decode())
        print(syncedTime)


def initiateSlave(port):
    slave = socket.socket()
    slave.connect(("127.0.0.1", port))
    sendThread = threading.Thread(target=sendTime, args=(slave,))
    sendThread.start()
    receiveThread = threading.Thread(target=receiveTime, args=(slave,))
    receiveThread.start()


if __name__ == "__main__":
    initiateSlave(5000)
