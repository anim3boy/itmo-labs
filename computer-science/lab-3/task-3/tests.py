import unittest
import script as task3


class TestTask3(unittest.TestCase):
    def test0(self):
        text = "20 + 22 = 42"
        ret = ("ok", "1205 + 1457 = 5297")
        
        self.assertEqual(task3.modificate(text), ret)
    
    def test1(self):
        text = "15263 + 123134 + 124 ^ 2 = 153773"
        ret = ("ok", "698877512 + 45485945873 + 46133 ^ 17 = 70938406592")
        
        self.assertEqual(task3.modificate(text), ret)

    def test2(self):
        text = "1, 2, 3, 4, 5, 6, 7"
        ret = ("ok", "8, 17, 32, 53, 80, 113, 152")
        
        self.assertEqual(task3.modificate(text), ret)
    
    def test3(self):
        text = "[1, 2, 3, 4, 5, 6, 7]"
        ret = ("ok", "[8, 17, 32, 53, 80, 113, 152]")
        
        self.assertEqual(task3.modificate(text), ret)
    
    def test3(self):
        text = "1230956256275315392856284012348237051525"
        ret = ("ok", "4545759914590019839484638780107891900211751817906764786805073441964476514476880")
        
        self.assertEqual(task3.modificate(text), ret)

    def test4(self):
        text = "Примеры целых чисел: -7, 222, 0, 569321, -12345 и др."
        ret = ("ok", "Примеры целых чисел: 152, 147857, 5, 972379203128, 457197080 и др.")
        
        self.assertEqual(task3.modificate(text), ret)

    def test5(self):
        text = "Примеры целых чисел: -8, 111, 0, 1285642, -20051 и так далее."
        ret = ("ok", "Примеры целых чисел: 197, 36968, 5, 4958626056497, 1206127808 и так далее.")
        
        self.assertEqual(task3.modificate(text), ret)
    
    