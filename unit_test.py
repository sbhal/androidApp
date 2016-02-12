import os
from time import sleep

import unittest

from appium import webdriver
from appium.webdriver.common.touch_action import TouchAction

# Returns abs path relative to this file and not cwd
PATH = lambda p: os.path.abspath(
    os.path.join(os.path.dirname(__file__), p)
)


class SimpleAndroidTests(unittest.TestCase):

    def setUp(self):
        
        desired_caps = {}
        desired_caps['platformName'] = 'Android'
        desired_caps['platformVersion'] = '6.0'
        desired_caps['deviceName'] = 'Android Emulator'
        desired_caps['app'] = PATH(
            '/home/sbhal/app.apk'
        )
        desired_caps['noReset'] = 'true'
        desired_caps['fullReset'] = 'false'
        desired_caps['appActivity'] = '.ComparisionApp'
        desired_caps['appPackage'] = 'com.example.sbhal.comparisionapp'
        desired_caps['autoLaunch'] = 'false'
        
        self.driver = webdriver.Remote(
            'http://localhost:4723/wd/hub', desired_caps)
        self.driver.reset()


    def tearDown(self):
        # end the session
        self.driver.quit()

    def test_comparision_app_reset(self):
        self.driver.launch_app()
        self.driver.reset()
        buttons = self.driver.find_elements_by_class_name('android.widget.Button')
        textfields = self.driver.find_elements_by_class_name("android.widget.EditText")
        self.assertEqual('X = Y', textfields[3].text)
        self.assertEqual('X = 0', textfields[1].text)
        self.assertEqual('Y = 0', textfields[2].text)

    def test_comparision_app_relaunch(self):
        self.driver.close_app()

        self.driver.launch_app()
        buttons = self.driver.find_elements_by_class_name('android.widget.Button')
        textfields = self.driver.find_elements_by_class_name("android.widget.EditText")
        self.assertEqual('X = Y', textfields[3].text)
        self.assertEqual('X = 0', textfields[1].text)
        self.assertEqual('Y = 0', textfields[2].text)

    def test_comparision_app_up_down(self):
        #need to call launch_app since default launch is off
        self.driver.launch_app()
        buttons = self.driver.find_elements_by_class_name('android.widget.Button')
        textfields = self.driver.find_elements_by_class_name("android.widget.EditText")
        for i in range(3):
            buttons[3].click() # Y = -3
        for i in range(10):
            buttons[0].click() # X = 10
        for i in range(13):
            buttons[2].click() # Y = 10
        self.assertEqual('X = Y', textfields[3].text)
        buttons[3].click() # Y = 9
        self.assertEqual('X > Y', textfields[3].text)
        buttons[1].click() 
        buttons[1].click() # X = 8
        self.assertEqual('X < Y', textfields[3].text)
        self.assertEqual('X = 8', textfields[1].text)
        self.assertEqual('Y = 9', textfields[2].text)
        
        self.driver.close_app()
        self.driver.launch_app()
        
        textfields = self.driver.find_elements_by_class_name("android.widget.EditText")
        self.assertEqual('X < Y', textfields[3].text)
        self.assertEqual('X = 8', textfields[1].text)
        self.assertEqual('Y = 9', textfields[2].text)
#        self.driver.quit()

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(SimpleAndroidTests)
    unittest.TextTestRunner(verbosity=2).run(suite)
