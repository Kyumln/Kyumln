# -*- coding: utf-8 -*-
import os
import io
import sys
import cv2
import time
import traceback
import numpy as np
import pandas as pd
from PIL import Image
from pathlib import Path
from tensorflow import keras
from webdriver import WebDriver


def generate_target_data(target_image):

    imgg = np.zeros((1, 60, 250), dtype=np.float32)
    img = cv2.imread(str(target_image['img_path'][0]))
    img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    img = cv2.resize(img, (250, 60))
    img = (img / 255.).astype(np.float32)
    imgg[0, :, :] = img

    indices = np.arange(len(imgg))

    curr_batch_idx = indices[0 * 16:(0 + 1) * 16]
    batch_len = len(curr_batch_idx)

    batch_images = np.ones((batch_len, 250, 60, 1),
                           dtype=np.float32)
    batch_labels = np.ones((batch_len, 5), dtype=np.float32)
    input_length = np.ones((batch_len, 1), dtype=np.int64) * \
                   (250 // 4 - 2)
    label_length = np.zeros((batch_len, 1), dtype=np.int64)

    img = imgg[0].T

    img = np.expand_dims(img, axis=-1)

    batch_images[0] = img

    batch_inputs = {
        'input_data': batch_images,
        'input_label': batch_labels,
        'input_length': input_length,
        'label_length': label_length,
    }
    return batch_inputs

def decode_batch_predictions(pred):
    characters = {0: '2', 1: '3', 2: '4', 3: '5', 4: '6', 5: '7', 6: '8', 7: 'a', 8: 'b', 9: 'c', 10: 'd', 11: 'e', 12: 'f', 13: 'g', 14: 'h', 15: 'k', 16: 'm', 17: 'n', 18: 'p', 19: 'r', 20: 'w', 21: 'x', 22: 'y'}
    labels_to_char = {0: '2', 1: '3', 2: '4', 3: '5', 4: '6', 5: '7', 6: '8', 7: 'a', 8: 'b', 9: 'c', 10: 'd', 11: 'e', 12: 'f', 13: 'g', 14: 'h', 15: 'k', 16: 'm', 17: 'n', 18: 'p', 19: 'r', 20: 'w', 21: 'x', 22: 'y'}

    pred = pred[:, :-2]
    input_len = np.ones(pred.shape[0]) * pred.shape[1]

    # Use greedy search. For complex tasks, you can use beam search
    results = keras.backend.ctc_decode(pred,
                                       input_length=input_len,
                                       greedy=True)[0][0]

    # Iterate over the results and get back the text
    output_text = []
    for res in results.numpy():
        outstr = ''
        for c in res:
            if c < len(characters) and c >= 0:
                outstr += labels_to_char[c]
        output_text.append(outstr)

    # return final text results
    return output_text

if __name__ == '__main__':
    try:
        BASE_DIR = os.getcwd()
        TARGET_DIR = Path(os.path.join(BASE_DIR, 'datasets', 'target_data'))

        # target_image = TARGET_DIR.glob("*.png")
        # df_target = pd.DataFrame(target_image, columns=["img_path"], index=None)
        #
        # result = generate_target_data(df_target)
        #
        model = keras.models.load_model('./model')
        model.summary()
        # preds = model.predict(result['input_data'])
        # pred_texts = decode_batch_predictions(preds)

        webdriver = WebDriver()

        add_save_point = "https://www.s2b.kr/S2BNCustomer/tcmo001.do"

        for i in range(0,500):
            # time.sleep(2)
            # webdriver.driver.execute_script("f_search();")

            webdriver.move_to_address(add_save_point)
            webdriver.driver.execute_script("window.open('');")
            webdriver.driver.switch_to.window(webdriver.driver.window_handles[-1])
            webdriver.move_to_address("https://www.s2b.kr/S2BNCustomer/Captcha")

            target = webdriver.driver.find_element_by_xpath("(//img)[1]")

            screenshot = target.screenshot_as_png
            image = Image.open(io.BytesIO(screenshot))

            save_directory = os.path.expanduser("./datasets/target_data")
            if not os.path.exists(save_directory):
                os.makedirs(save_directory)

            file_path = os.path.join(save_directory, "target.png")
            image.save(file_path)
            webdriver.driver.close()

            target_image = TARGET_DIR.glob("*.png")
            df_target = pd.DataFrame(target_image, columns=["img_path"], index=None)

            result = generate_target_data(df_target)

            model = keras.models.load_model('./model')
            preds = model.predict(result['input_data'])
            pred_texts = decode_batch_predictions(preds)

            webdriver.driver.switch_to.window(webdriver.driver.window_handles[0])
            webdriver.driver.execute_script("document.getElementsByName('answer')[0].value = '"+pred_texts[0]+"'")

            webdriver.move_to_address(add_save_point)

        webdriver.driver.quit()
    except Exception as e:
        print("An error occurred:", e)
        print(traceback.format_exc())
        webdriver.driver.quit()
        sys.exit(1)