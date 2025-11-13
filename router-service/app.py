import requests
from flask import Flask, jsonify, redirect
from datetime import datetime, time

app = Flask(__name__)

# Супер простая реализация, лишь бы сдать лабу и дойти до мвп 
# нужно будет переделать сервис под учет всех полей БД при роутинге
# ипользовать celery для распараллеливания перед их обработкой

@app.route('/<uuid:route_uuid>')
def route(route_uuid):
    response = requests.get(f'http://localhost:8082/api/code/{route_uuid}')
    try:
        data = response.json()
    except ValueError:
        return redirect('/error', code=302)

    for rule in data['rules']:
        if isRuleValid(rule):
            return redirect(rule['routeURL'], code=302)

    return redirect('/error', code=302)

def isRuleValid(rule):
    now = datetime.now()
    start_time_str = rule.get('startTime')
    end_time_str = rule.get('endTime')

    start_time = datetime.strptime(start_time_str, "%H:%M:%S").time() if start_time_str else time.min
    end_time = datetime.strptime(end_time_str, "%H:%M:%S").time() if end_time_str else time.max

    start_date_str = rule.get('startDate')
    end_date_str = rule.get('endDate')

    start_date = datetime.fromisoformat(start_date_str) if start_date_str else datetime.min
    end_date = datetime.fromisoformat(end_date_str) if end_date_str else datetime.max

    if (start_time < now.time() < end_time and
        start_date < now < end_date and
        rule['weekDays'][now.weekday()]):
        return True
    return False

if __name__ == '__main__':
    app.run(debug=True)