#!/bin/bash

# Prepare environment
cd ~

# until rm -rf `find lab0 2> /dev/null` 2> /dev/null ; do
#     chmod -R 777 lab0 2> /dev/null
# done

until rm -rf lab0 2> /dev/null ; do
    chmod -R 777 lab0 2> /dev/null
done

# Dirs

mkdir -p lab0/eevee2/tyrogue

cd lab0

mkdir -p froslass5/fearow
mkdir froslass5/corphish
mkdir froslass5/shelmet
mkdir froslass5/hariyama

mkdir -p monferno9/magneton

# Files

echo "Живет Forest" > eevee2/escavalier
echo "satk=7 sdef=6
spd=8" > eevee2/mamoswine
echo "Ходы  Bind Dark Pulse Giga Drain Icy Wind Magic Coat
Pain Split Sleep Talk Snore Spite Trick" > eevee2/jellicent
echo "Возможности
Overland=2 Sky=8 Jump=2 Power=2 Intellingence=4
Sinker=0" > eevee2/mothim

echo "weight=19.8 height=20.0 atk=6 def=4" > monferno9/nidoranM
echo "Живет
Forest Grassland Urban" > monferno9/staravia
echo "Живет  Grassland
Mountain" > monferno9/nidoking

echo "Ходы  Body Slam Confuse Ray‡ Covet Dark Pulse
Double-Edge Fire Spin Foul Play Heat Wave Iron Tail Magic Coat Nasty
Plot‡ Ominous Wind Pain Split Quick Attack Role Play Safeguard‡ Sleep
Talk Snore Spite Swift Zen Headbutt" > ninetales8
echo "Ходы  Aqua Tail Body Slam
Dive Double-Edge Drill Run Icy Wind Iron Tail Signal Beam Sleep Talk
Snore" > seel4
echo "Тип покемона  NORMAL NONE" > skitty4

# Chmod

chmod ugo+wx-r eevee2
chmod 664 eevee2/{escavalier,mamoswine}
chmod 444 eevee2/jellicent
chmod 400 eevee2/mothim
chmod 753 eevee2/tyrogue

chmod 576 froslass5{,/fearow}
chmod 551 froslass5/corphish
chmod 335 froslass5/shelmet
chmod 537 froslass5/hariyama

chmod 312 monferno9
chmod 771 monferno9/magneton
chmod 444 monferno9/nidoranM
chmod 620 monferno9/staravia
chmod ugo=r monferno9/nidoking

chmod 004 ninetales8
chmod 404 seel4
chmod 440 skitty4

# Copies and links

# FIX "cp: cannot open ninetales8: Permission denied"
chmod u+r ninetales8
# /FIX
cp ninetales8 eevee2/jellicentninetales
# FIX "cp: cannot open ninetales8: Permission denied"
chmod u-r ninetales8
# /FIX

cat monferno9/{nidoranM,staravia} > seel4_26
ln -s seel4 eevee2/mamoswineseel
ln seel4 monferno9/staraviaseel
cp -p seel4 monferno9/magneton
ln -s froslass5 Copy_11

# FIX "cp: monferno9: Permission denied"
chmod u+r monferno9
# /FIX
cp -r monferno9 froslass5/shelmet
# FIX "cp: monferno9: Permission denied"
chmod u-r monferno9
# /FIX

ls -lR

# Tasks

cat <<EOF

  4. Tasks

EOF

echo '    1)'
# find . -name 'e*' -type f -exec wc -m '{}' ';' 2> /dev/null | sort -rn
wc -m ./{,*/,*/*/,*/*/*/,*/*/*/*/}e* 2> /dev/null | egrep -v '[ \t]+total' | sort -rn

echo '    2)'
# find . -name 'n*' -type f -ls 2> /tmp/${LOGNAME}err | sort -rn -k 4 | head -4
ls -l ./{,*/,*/*/,*/*/*/,*/*/*/*/}n* 2> /tmp/${LOGNAME}err | sort -rn -k 2 | head -4

echo '    3)'
# find . -name 'n*' -type f -exec cat -n '{}' ';' 2>&1 | sort -r -k 2
cat -n ./{,*/,*/*/,*/*/*/,*/*/*/*/}n* 2>&1 | sort -r -k 2

echo '    4)'
cat eevee2/{jellicent,mothim} 2> /dev/null | sort

echo '    5)'
# find . -type f -ls 2> /tmp/${LOGNAME}err2 | sort -n -k 4 | tail -4
ls -l ./{,*/,*/*/,*/*/*/,*/*/*/*/}* 2> /tmp/${LOGNAME}err2 | sort -n -k 2 | tail -4

echo '    6)'
# find . -name 'n*' -type f -exec cat -n '{}' ';' 2> /tmp/${LOGNAME}err3 | sort -k 2
cat -n ./{,*/,*/*/,*/*/*/,*/*/*/*/}n* 2> /tmp/${LOGNAME}err3 | sort -k 2

# Remove

rm -f ninetales8 
rm eevee2/mamoswine 

# FIX "eevee2/mamoswinese*: No such file or directory" 
chmod u+r eevee2 
# /FIX 
rm eevee2/mamoswinese* 
# FIX "eevee2/mamoswinese*: No such file or directory" 
chmod u-r eevee2 
# /FIX 

rm -f monferno9/staraviase* 

# FIX "rm: cannot read directory monferno9: Permission denied" 
chmod u+r monferno9 
# /FIX 
rm -rf monferno9 

rm -r froslass5/fearow
