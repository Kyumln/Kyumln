import datetime
import re


def get_past_time() -> tuple:
    """
    Get current, Past Times // TenMinutesBefore
    :return tuple: (currentTime, PastTime)
    """
    cur_time = datetime.datetime.now()
    ten_minutes_before = cur_time - datetime.timedelta(minutes=10)
    ten_minutes_before = ten_minutes_before.strftime("%Y-%m-%d %H:%M:%S")
    month = "{:02d}".format(cur_time.month)
    target_times = (cur_time, ten_minutes_before, cur_time.year, month)

    return target_times


def remove_img_tags_from_html(result) -> list:
    """
    remove img tag in target html
    :param result: result that have data like bid_name, whole HTML from DB
    :return list: list that img tag is removed
    """
    bid_info = len(result) * [0]
    pattern = r'<img\s.*?>'

    for idx, item in enumerate(result, 0):
        img_deleted_item = re.sub(pattern, '', item[2], flags=re.DOTALL)
        bid_info[idx] = (item[1], img_deleted_item)

    return bid_info


def remove_special_char(bid_name) -> list:
    """
    remove special character add to tag
    :param bid_name: it just split bid_name and use those to make post
    :return list: tag_list that add to tag
    """
    removed_special_str = bid_name
    special_str = "{}[](),"
    for item in special_str:
        removed_special_str = removed_special_str.replace(item, " ")

    tag_list = removed_special_str.split(" ")

    return tag_list
