import datetime
import threading
import time
from dateutil import parser
import socket

client_data = {}


def startReceivingTime(connector, address):
    clock_str = connector.recv(1024).decode()
    clock_time = parser.parse(clock_str)
    time_diff = datetime.datetime.now() - clock_time

    client_data[address] = {
        "clock_time": clock_time,
        "time_diff": time_diff,
        "connector": connector,
    }
    print("Client updated with" + str(address))
    time.sleep(5)


def startConnecting(masterServer):
    while True:
        connector, address = masterServer.accept()
        slaveAddr = str(address[0]) + ":" + str(address[1])
        print(slaveAddr + " connected")
        currentThread = threading.Thread(
            target=startReceivingTime,
            args=(
                connector,
                address,
            ),
        )
        currentThread.start()


def getAvgTimeDiff():
    timeDiff = list(client["time_diff"] for client_addr, client in client_data.items())
    sumDiff = sum(timeDiff, datetime.timedelta(0, 0))
    avgDiff = sumDiff / len(client_data)
    return avgDiff


def syncAllClocks():
    while True:
        print("New synchronization cycle started.")
        if len(client_data) > 0:
            avgTimeDiff = getAvgTimeDiff()
            try:
                for client_addr, client in client_data.items():
                    syncTime = datetime.datetime.now() + avgTimeDiff
                    client["connector"].send(str(syncTime).encode())

            except Exception as e:
                print(e)
        time.sleep(5)


def initiateClockServer(port):
    masterServer = socket.socket()
    masterServer.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    masterServer.bind(("", port))
    masterServer.listen(10)

    masterThread = threading.Thread(
        target=startConnecting,
        args=(masterServer,),
    )
    masterThread.start()
    syncThread = threading.Thread(
        target=syncAllClocks,
        args=(),
    )
    syncThread.start()


if __name__ == "__main__":
    initiateClockServer(5000)
