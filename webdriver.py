import time
import pyperclip
from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class WebDriver:
    def __init__(self, driver_location="driver/chromedriver.exe"):
        """
        :param str driver_location: webDriver's location
        """
        self.driver_location = driver_location

        self.chrome_options = webdriver.ChromeOptions()
        self.chrome_options.add_experimental_option('prefs', {'profile.default_content_setting_values.clipboard': 1})

        self.driver = webdriver.Chrome(executable_path=self.driver_location, options=self.chrome_options)
        self.wait = WebDriverWait(self.driver, 4)
        self.turn_on_implicitly_wait(5)

        self.action_controller = ActionChains(self.driver)

    @property
    def driver_location(self) -> str:
        return self.__driver_location

    @driver_location.setter
    def driver_location(self, new_driver_location) -> None:
        if not isinstance(new_driver_location, str):
            raise ValueError("Url must be a str, not a {type}".format(type=type(new_driver_location)))
        self.__driver_location = new_driver_location

        return None

    def turn_off_implicitly_wait(self) -> None:
        self.driver.implicitly_wait(0)

        return None

    def turn_on_implicitly_wait(self, value=15) -> None:
        self.driver.implicitly_wait(value)

        return None

    def reset_action_controller(self) -> None:
        """
        Method that reset driver's action controller
        :return None:
        """
        self.action_controller = ActionChains(self.driver)

        return None

    def move_to_address(self, address) -> None:
        """
        renamed selenium method .get to more explicit
        :param str address: target address
        :return None:
        """
        self.driver.get(address)

        return None

    def copy_and_paste_variable_to_target(self, variable, target) -> None:
        """
        copy variable and paste to target // TEXT ONLY
        :param str variable: variable to copy
        :param webElement target: webElement that u paste variable
        :return:
        """
        target.click()
        pyperclip.copy(variable)
        target.send_keys(Keys.CONTROL, 'v')

        return None

    def check_exception(self, path) -> None:
        """
        check page's exception element that only appear specific condition
        :return:
        """
        self.turn_off_implicitly_wait()
        try:
            self.wait.until(EC.element_to_be_clickable((By.XPATH, path))).click()
        except Exception as TimeoutException:
            pass
        try:
            self.wait.until(EC.element_to_be_clickable((By.XPATH, path))).click()
        except Exception as TimeoutException:
            pass

        self.turn_on_implicitly_wait()

        return None

    def copy_bid_detail(self, bid_info) -> None:
        """
        copy bidDetail to clipboard use empty page // can copy simple chart
        :param list bid_info: list from DB that have html
        :return:
        """
        self.driver.execute_script("window.open('');")
        self.driver.switch_to.window(self.driver.window_handles[-1])
        self.driver.execute_script('document.querySelector("body").insertAdjacentHTML("beforeend", `{html}`);'.format(html=bid_info[1]))

        target = self.driver.find_element_by_xpath("//body")
        self.reset_action_controller()
        self.action_controller.move_to_element(target).click().key_down(Keys.CONTROL).send_keys('a').send_keys('c').key_up(Keys.CONTROL).perform()

        time.sleep(2)
        self.reset_action_controller()
        self.driver.close()
        self.driver.switch_to.window(self.driver.window_handles[0])

        return None