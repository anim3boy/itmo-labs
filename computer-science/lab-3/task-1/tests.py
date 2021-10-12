import unittest
import script as task1

pattern = task1.generate_pattern(335050)
pattern = pattern[1]

class TestTask1(unittest.TestCase):
    def test1(self):
        string = "%s" % pattern
        self.assertEqual(task1.blackbox(string=string), ("ok", 1))

    def test2(self):
        string = "%s" % pattern * 2
        self.assertEqual(task1.blackbox(string=string), ("ok", 2))    

    def test3(self):
        string="%s" % pattern * 3
        self.assertEqual(task1.blackbox(string=string), ("ok", 3))

    def test4(self):
        string = \
            "%s Cause I still feel your presence" \
            "Flowing through my veins" \
            "%s I can only blame so much" \
            "On my fucked up brain" \
            "%s And the pills that I've been taking" \
            "Just accentuate the pain" \
            "So I'll build my own wooden home" \
            "%sTo rest my frail frame" % ((pattern, )*4)
            
        self.assertEqual(task1.blackbox(string=string), ("ok", 4))

    def test5(self):
        string = r"O{-OO:OO--::--O{:{O{:O-:::-:-{{-O:{-:OO-O--:::O::{:-::{O:-:-:-OO:O{OO-:{O-{-{O{{::O{O-:{-{-OO:O-O:-:{-{O{{--:--O:{{-::O-{:--:OOO{O{OO-O:O:O{-O:{OOO---::{::{:{O{:O:{--:{:::-{::--{-{-:-{-{::{-:-O:--{-{{O-{-{:OO{---{{{O{{-O:{{O-{-:OO{{:::{{:-OO:O{{OO--{{:::OO-OO-{{:O{O-{{::{O:O:O{O{:O--{-O{O-{O:-:---OO-:--{{-{::OO:---:-O--O-:{-:{-:-:{{{--:-:::O:-:{{{::OO{:OO:::O:{O---{-:OO-{{:-:{:::{::{{-:-{{:OO-OO{-:O-::-O--{{:-{-OO{:O{OOOO--:-{-OO:-O:::O{OO--O{-O::-{{::-:{O{OO:O-{::OO:{:{{{:O::{OOO:::-::-::-{-:-:O:-OO:OO:OO-OO-{O{{-{-OO-:O{::{O{O{O-{-:OO{O{O-O:OO{-OO-O-O--:OO:{-OOO{O:--{O:O{--{OO{-{-:-:{-O-O:{{{::O:{-{O:-{{O{:O-OO:O{-{:-{{O:-:OO-O-{-O-:::{OO--:--{:-O::-OO:---{{:-{{--O{{O{:-:{-{:::-:{-O{O-OOO{O--{{::-O{{O{:{O--:{O-{{--:{--{::-{{-O{{O:::{O{-:{::{{--::O{-{O--O{{{:-OOOOO-O:{:O:{-{{{-:{::::{O{O:-:{--O:O::OO{:O:{OO-O::OO:{{:--{O{{-{:{O-{-{O{{--::{OO{:-{{O-O:O-{O-O-O:{{:-{--O{O--{-{:::OOO{{-:OO:{-:{{--O::-{O:-:{:-::O-:O:-O:{O{:{{:-O-OOO{:{-{:{O{O--OO:--OO:-{O{O::-:{--{-:{{:{{-{OOO{::{O----{O:O"
            
        self.assertEqual(task1.blackbox(string=string), ("ok", 2))
    