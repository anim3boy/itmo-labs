#!/usr/bin/env bash

chmod -R 777 lab0
rm -rf lab0
# echo "lab0 has been deleted..."
# echo "Part 1"

mkdir -p lab0
cd lab0

mkdir -p grovyle5/{purrloin,xatu} spheal5/{beautifly,petilil,shellder,snover} vulpix0/{tympole,lileep}
touch bulbasaur3 caterpie3 sandshrew0 grovyle5/grimer grovyle5/gengar spheal5/dewott spheal5/venusaur vulpix0/gallade vulpix0/pineco vulpix0/gligar vulpix0/munchlax

echo -e "Ходы  Bind Body Slam Bullet Seed Defense Curl Fury Cutter\nGiga Drain Grass Pledge Knock Off Mud-Slap Natural Gift Secret Power\nSeed Bomb Sleep Talk Snope String Shot Synthesis Worry\nSeed" > bulbasaur3
echo -e "Развитые способности  Run Away" > caterpie3
echo -e "Ходы  Body\nSlam Covet Double-Edge Dynamicpunch Earth Power Focus Punch Fury\nCutter Iron Tail Knock Off Mud-Slap Rollout Seismic Toss Sleep Talk\nSnope Stealth Rock Super Fang Swift" > sandshrew0
cd grovyle5
echo -e "weigth=66.1\nheight=35.0 atk=8 def=5" > grimer
echo -e "Живет  Urban" > gengar
cd ../
cd spheal5
echo -e "Тип покемона  WATER\nNONE" > dewott
echo -e "weigth=220.5 height=79.0 atk=8 def=8" > venusaur
cd ../
cd vulpix0
echo -e "Ходы\nBody Slam Defense Curl Double-Edge Drain Punch Dual Chop Fire Punch\nFocus Punch Fury Cutter Helping Hand Hyper Voice Ice Punch Knock Off\nLeaf Blade‡ Low Kick Magic Coat Magic Room Mud-Slap Night Slash‡ Pain\nSplit Recycle Role Play Shock Wave Signal Beam Skill Swap Sleep Talk\nSnatch Snore Swift hunderpunch Trick Vacuum Wave Wonder Room Zen\nHeadbutt" > gallade
echo -e "Возможности  Overland=3 Jump=4 Power=1 Intelligence=3\nSinker=0 Threaded=0" > pineco
echo -e "Развитые способности\nImmunity" > gligar
echo -e "Живет  Forest Mountain Taiga Urban" > munchlax
cd ../

# echo "Part 2"

chmod 404 bulbasaur3
chmod 006 caterpie3
chmod 330 grovyle5
chmod 753 grovyle5/purrloin
chmod 064 grovyle5/grimer
chmod 004 grovyle5/gengar
chmod 551 grovyle5/xatu
chmod 640 sandshrew0
chmod 333 spheal5
chmod 004 spheal5/dewott
chmod 537 spheal5/beautifly
chmod 570 spheal5/petilil
chmod 640 spheal5/venusaur
chmod 337 spheal5/shellder
chmod 777 spheal5/snover
chmod 570 vulpix0
chmod 751 vulpix0/tympole
chmod 620 vulpix0/gallade
chmod 006 vulpix0/pineco
chmod 664 vulpix0/gligar
chmod 363 vulpix0/lileep
chmod 004 vulpix0/munchlax

# -------------------------------------------------------------- #

# echo "Part 3"

ln -s "`pwd`/grovyle5" Copy_80

chmod 706 caterpie3 
cp caterpie3 spheal5/snover
chmod 006 caterpie3

chmod 704 bulbasaur3
chmod 770 vulpix0
ln -s "`pwd`/bulbasaur3" vulpix0/munchlaxbulbasaur
chmod 404 bulbasaur3
chmod 570 vulpix0

chmod 706 caterpie3
chmod 770 vulpix0
cat caterpie3 > vulpix0/pinecocaterpie
chmod 006 caterpie3
chmod 570 vulpix0

chmod 704 bulbasaur3
chmod 770 vulpix0
ln "`pwd`/bulbasaur3" vulpix0/gligarbulbasaur
chmod 404 bulbasaur3
chmod 570 vulpix0

chmod -R 700 spheal5
cp -r spheal5 vulpix0/lileep
chmod 333 spheal5
chmod 004 spheal5/dewott
chmod 537 spheal5/beautifly
chmod 570 spheal5/petilil
chmod 640 spheal5/venusaur
chmod 337 spheal5/shellder
chmod 777 spheal5/snover

chmod 764 grovyle5/grimer
chmod 704 grovyle5/gengar
cat grovyle5/grimer grovyle5/gengar > bulbasaur3_15
chmod 064 grovyle5/grimer
chmod 004 grovyle5/gengar

# echo "Part 4" 

# wc -m v* ./*/v* ./*/*/v* | sort -n
ls -Rl 2> /tmp/lab0 | grep "^[^t.].*ti.*" | sort -k 2 -n -r  #needs to test
chmod 700 spheal5
cd ./spheal5; for i in `ls -p | grep "[^/]$"`; do cat $i -n 2> /dev/null | grep -i "y$" ; done; cd ../;
chmod 333 spheal5
ls -Rl 2> /dev/null | grep "^-" | head -4 | sort -k7
cat s* ./*/s* ./*/*/s* 2> /dev/null | sort -r  
ls -Rl --time-style="+%Y%m%d%H%M%S" 2>&1 | grep "^-" | head -3 |sort -k6 -n -r

# echo "Part 5" 


rm -f sandshrew0

rm -f spheal5/dewott

rm -f Copy_*


chmod -R 700 vulpix0
rm -f vulpix0/gligarbulbasa*
chmod 770 vulpix0


chmod -R 700 grovyle5
rm -rf grovyle5

rm -rf lab0/vulpix0/lileep
