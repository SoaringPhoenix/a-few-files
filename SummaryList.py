# coding=utf-8
# Author:chengwanding@zuoyebang.com
# Date:2019/02/13
# Desc:汇总各方向项目相关信息并邮件周知
from datetime import datetime, timedelta
from common.notify import send_mail
from jira_stats.jira_task_weekly_report import get_jira_tasks
from common.jira_sdk import JiraRobot

receive = "chengwanding@zuoyebang.com"
robot = JiraRobot("qa", "asdf1234")
now = datetime.now()
search_start = (now + timedelta(days=-30)).strftime("%Y-%m-%d")
search_end = (now + timedelta(days=+1)).strftime("%Y-%m-%d")
week_start = (now + timedelta(days=-14)).strftime("%Y-%m-%d 12:00:00")
week_end = (now + timedelta(days=0)).strftime("%Y-%m-%d 12:00:00")
summary = u"汇总表（%s~%s）" % (week_start, week_end)
html = ""

tasks = get_jira_tasks(robot, 'PTJCQAPM', search_start, search_end, week_start, week_end)
total = len(tasks)
programming = 0
testing = 0
online = 0
rdDelay = 0
qaDelay = 0
mrdChanges = 0
risky = 0

for task in tasks:
    if task[2] == 'doing' or task[2] == 'Doing':
        testing += 1
    if (task[12] is not None) or (task[12] != ''):
        online += 1
    if task[3] == u'是':
        rdDelay += 1
    if task[4] == u'是':
        qaDelay += 1
    if task[7] == u'是':
        mrdChanges += 1

issues = robot.get_recent_issues('PTJCQAPM', search_start, search_end)
for issue in issues:
    fields = issue.raw["fields"]
    if fields['customfield_11002'] is not None and fields['customfield_11002'] != '' \
            and fields['customfield_11002'] != u'无' and fields['customfield_11002'] != u'暂无':
        risky += 1
print "testing=" + str(testing)
print "total=" + str(total)
print "online=" + str(online)
print "rdDelay=" + str(rdDelay)
print "qaDelay=" + str(qaDelay)
print "mrdChanges=" + str(mrdChanges)
print "risky=" + str(risky)

# send_mail(receive, summary, html)


def summary_html(robot, project):
    content = ''
    return content


