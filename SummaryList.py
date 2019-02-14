# coding=utf-8
# Author:chengwanding@zuoyebang.com
# Date:2019/02/13
# Desc:汇总各方向项目相关信息并邮件周知
from datetime import datetime, timedelta
from common.notify import send_mail
from jira_stats.jira_task_weekly_report import get_jira_tasks
from common.jira_sdk import JiraRobot


class SummaryList:
    def __init__(self):
        self.robot = JiraRobot("qa", "asdf1234")
        self.receive = "chengwanding@zuoyebang.com"
        self.now = datetime.now()
        self.search_start = (self.now + timedelta(days=-30)).strftime("%Y-%m-%d")
        self.search_end = (self.now + timedelta(days=+1)).strftime("%Y-%m-%d")
        self.week_start = (self.now + timedelta(days=-7)).strftime("%Y-%m-%d 12:00:00")
        self.week_end = (self.now + timedelta(days=0)).strftime("%Y-%m-%d 12:00:00")
        self.project_list = ['SCQAPM', 'GGQAPM', 'PTJCQAPM', 'KDQAPM', 'JZQAPM']
        self.project_dict = {'SCQAPM': u'商城', 'GGQAPM': u'广告', 'PTJCQAPM': u'平台基础', 'KDQAPM': u'快对作业',
                             'JZQAPM': u'家长帮'}

    def summary_data(self, project):
        arr = []
        for index in range(len(project)):
            ret = []
            tasks = get_jira_tasks(self.robot, project[index], self.search_start, self.search_end, self.week_start,
                                   self.week_end)
            issues = self.robot.get_recent_issues(project[index], self.search_start, self.search_end)
            total, programming, testing, online, rdDelay, qaDelay, mrdChanges, risky, todo = (0, 0, 0, 0, 0, 0, 0, 0, 0)
            for task in tasks:
                if task[0] in ['aac', 'aad']:
                    continue
                if task[2] == 'doing' or task[2] == 'Doing':
                    testing += 1
                if (task[12] is not None) or (task[12] != ''):
                    online += 1
            for issue in issues:
                fields = issue.raw["fields"]
                sorts = fields["summary"].split(u"】")
                if (fields["customfield_10818"] is not None and fields["customfield_10818"] > self.week_end) \
                    or (fields["customfield_10819"] is not None and fields["customfield_10819"] < self.week_start) \
                    or (fields["customfield_10818"] is None and (fields["created"] < self.week_start)) \
                    or (fields["customfield_10819"] is None and (fields["created"] < self.week_start))\
                        or ('opic' in sorts[0] and u"【线上" in sorts[0]):
                    continue
                if fields["customfield_10900"] is not None and fields["customfield_10900"]["value"] == u'是':
                    rdDelay += 1
                if fields["customfield_10901"] is not None and fields["customfield_10901"]["value"] == u'是':
                    qaDelay += 1
                if fields["customfield_11001"] is not None and fields["customfield_11001"]["value"] == u'是':
                    mrdChanges += 1
                if fields['customfield_11002'] is not None and fields['customfield_11002'] != '' \
                        and fields['customfield_11002'] != u'无' and fields['customfield_11002'] != u'暂无':
                    risky += 1
                total += 1
            ret.append(self.project_dict[project[index]])
            ret.append(total)
            ret.append(testing)
            ret.append(online)
            ret.append(rdDelay)
            ret.append(qaDelay)
            ret.append(mrdChanges)
            ret.append(risky)
            arr.append(ret)
        return arr
        # print "testing=" + str(testing)
        # print "total=" + str(total)
        # print "online=" + str(online)
        # print "rdDelay=" + str(rdDelay)
        # print "qaDelay=" + str(qaDelay)
        # print "mrdChanges=" + str(mrdChanges)
        # print "risky=" + str(risky)

    def summary_html(self):
        table_header = u"""
        <table style="box-sizing: border-box; border-spacing: 0px; border-collapse: collapse; margin-top: 0px; margin-bottom: 16px; display: block; overflow: auto; color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;; font-size: 16px;">
            <thead style="box-sizing: border-box;">
                <tr style="box-sizing: border-box; border-top: 1px solid rgb(198, 203, 209);">
                    <th style="box-sizing: border-box; width:100px; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);">方向</th>
                    <th style="box-sizing: border-box; width:100px; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);">项目总数</th>
                    <th style="box-sizing: border-box; width:100px; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);">测试中项目数</th>
                    <th style="box-sizing: border-box; width:100px; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);">本周上线数</th>
                    <th style="box-sizing: border-box; width:100px; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);">提测延期数</th>
                    <th style="box-sizing: border-box; width:100px; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);">测试延期数</th>
                    <th style="box-sizing: border-box; width:100px; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);">需求变更项目数</th>
                    <th style="box-sizing: border-box; width:100px; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);">有风险项目数</th>
                </tr>
            </thead>
            <tbody style="box-sizing: border-box;">"""
        table_tail = u"""</tbody></table>"""
        tr_format = [u"""
            <tr style="box-sizing: border-box; border-top: 1px solid rgb(198, 203, 209);">
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:left;">%s</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
            </tr>""", u"""
            <tr style="box-sizing: border-box; background-color: rgb(246, 248, 250); border-top: 1px solid rgb(198, 203, 209);text-align:center;">
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:left;">%s</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
                <td style="box-sizing: border-box; padding: 6px 13px; border: 1px solid rgb(223, 226, 229);text-align:center;">%d</td>
            </tr>"""]
        table = table_header
        stats = self.summary_data(self.project_list)
        print stats
        row = 0
        for stat in stats:
            tr = tr_format[row] % (stat[0], stat[1], stat[2], stat[3], stat[4], stat[5], stat[6], stat[7])
            table += tr
            row = (row + 1) % 2
        table += table_tail
        return table

    def execute(self):
        summary = u"汇总表（%s~%s）" % (self.week_start, self.week_end)
        html = self.summary_html()
        send_mail(self.receive, summary, html)


obj = SummaryList()
obj.execute()
