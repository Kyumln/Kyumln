import json
from collections import Counter
import matplotlib
import crawler
from wordcloud import WordCloud
from konlpy.tag import Okt
from matplotlib import pyplot as plt
import matplotlib.font_manager as fm
import pandas as pd

while True:
    print("---------------------------------------------")
    print("-1. 헤드라인 검색 | 2. DB 접속 | 3. 프로그램 종료-")
    print("---------------------------------------------")
    num = input(">>> ")

    if num == '1':
        filename = crawler.main()
        counter = Counter()
        df = pd.read_json(filename)

        with open(filename, 'r', encoding='utf-8') as f:
            sample = json.load(f)

        okt = Okt()
        for i in range(0, 1000):
            temp_str = sample[i]['title']
            noun = okt.nouns(temp_str)
            words = [n for n in noun if len(n) > 1]
            counter = counter + Counter(words)

        font_path = 'D:\Minkyu\JAVA\Monaco 맑은 고딕.ttf'
        font_prop = fm.FontProperties(fname=font_path)
        font_name = font_prop.get_name()
        matplotlib.rc('font', family=font_name)

        noun_list = counter.most_common(50)
        df_noun_list = pd.DataFrame(noun_list, columns=['noun', 'value'])
        df_noun_list.plot.bar(x='noun', y='value', xlabel='키워드', ylabel='언급된 횟수', fontsize=9, figsize=(10, 5), rot=80)
        plt.show()

        df['pDate'] = pd.to_datetime(df['pDate'])
        df['pDate'] = df['pDate'].dt.strftime('%Y-%m-%d')
        counter_date = Counter(df['pDate'])
        counted_data = counter_date.most_common()
        sorted_data = sorted(counted_data, key=lambda counted_data: counted_data[0], reverse=False)
        date_df = pd.DataFrame(sorted_data, columns=['date', 'value'])

        plt.Figure(figsize=(12, 5))
        plt.xlabel("날짜")
        plt.ylabel("검색어와 관련된 기사가 작성된 횟수")
        plt.grid(True)
        plt.rc('font', size=8)
        plt.plot(range(len(date_df)), date_df['value'])
        plt.xticks(range(len(date_df)), [text for text in date_df['date']], rotation=60)
        plt.show()

        wc = WordCloud(font_path='D:\Minkyu\JAVA\Monaco 맑은 고딕.ttf', width=400, height=400, scale=2.0, max_font_size=250)
        gen = wc.generate_from_frequencies(counter)
        plt.figure()
        plt.imshow(gen)
        plt.show()

    if num == '2':
        print("---------------------------------------")
        print("-1. 원하는 데이터 검색 및 시각화 | 2. 나가기-")
        print("---------------------------------------")
        num = input(">>> ")

        if num == '1':
            print("---------------------------------------")
            print("-검색하고자 하는 파일의 이름을 입력해 주세요.-")
            print("---------------------------------------")
            input_filename = input(">>> ")
            filename='%s_naver_news.json' % (input_filename)
            counter = Counter()
            df = pd.read_json(filename)

            with open(filename, 'r', encoding='utf-8') as f:
                sample = json.load(f)

            okt = Okt()
            for i in range(0, 1000):
                temp_str = sample[i]['title']
                noun = okt.nouns(temp_str)
                words = [n for n in noun if len(n) > 1]
                counter = counter + Counter(words)

            font_path = 'D:\Minkyu\JAVA\Monaco 맑은 고딕.ttf'
            font_prop = fm.FontProperties(fname=font_path)
            font_name = font_prop.get_name()
            matplotlib.rc('font', family=font_name)

            noun_list = counter.most_common(50)
            df_noun_list = pd.DataFrame(noun_list, columns=['noun', 'value'])
            df_noun_list.plot.bar(x='noun', y='value', xlabel='키워드', ylabel='언급된 횟수', fontsize=9, figsize=(10, 5),
                                  rot=80)
            plt.show()

            df['pDate'] = pd.to_datetime(df['pDate'])
            df['pDate'] = df['pDate'].dt.strftime('%Y-%m-%d')
            counter_date = Counter(df['pDate'])
            counted_data = counter_date.most_common()
            sorted_data = sorted(counted_data, key=lambda counted_data: counted_data[0], reverse=False)
            date_df = pd.DataFrame(sorted_data, columns=['date', 'value'])

            plt.Figure(figsize=(12, 5))
            plt.xlabel("날짜")
            plt.ylabel("검색어와 관련된 기사가 작성된 횟수")
            plt.grid(True)
            plt.rc('font', size=8)
            plt.plot(range(len(date_df)), date_df['value'])
            plt.xticks(range(len(date_df)), [text for text in date_df['date']], rotation=60)
            plt.show()

            wc = WordCloud(font_path='D:\Minkyu\JAVA\Monaco 맑은 고딕.ttf', width=400, height=400, scale=2.0,
                           max_font_size=250)
            gen = wc.generate_from_frequencies(counter)
            plt.figure()
            plt.imshow(gen)
            plt.show()


        if num == '2':
            continue

    if num == '3':
        print("프로그램을 종료합니다.")
        break
