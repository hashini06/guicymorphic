##################################################
#                                                #
# This script will create checksums for all the  #
# files recursively from the current directory   #
# or from the directory supplied as an argument. #
# It will remove possibly existing checksum and  #
# tmp files and create new checksums (SHA1, MD5) #
# and save them into files.                      #
#                                                #
# Last update: 04.04.2007
# Script by: Mikko Wuokko                        #
#            &ltmikko.wuokko@evtek.fi>             #
#                                                #
##################################################
# Setting up the base dir
if [ -n "$1" ]
then
   REPO_HOME=$1
else
   REPO_HOME=$PWD
fi
# Moving to the repository
cd ${REPO_HOME}
# Creating the new checsums
echo Creating checksums:
# Looping through the files of the current directory
for i in `find * -type d`
do
  echo Recursing into directory \'$i\' \(${REPO_HOME}/$i\)
  cd ${REPO_HOME}/$i
   # Removing old checksum files and possible tmp files
   COUNT=`find * -maxdepth 0 -type f | wc -l`
   if [ "$COUNT" -gt 0 ]
   then
     # Check and remove existing checksum and redundant tmp files
     COUNTSHA=`find * -maxdepth 0 -type f -name "*.sha1"| wc -l`
     if [ "$COUNTSHA" -gt 0 ]
     then
        rm *.sha1
     fi
     COUNTMD5=`find * -maxdepth 0 -type f -name "*.md5"| wc -l`
     if [ "$COUNTMD5" -gt 0 ]
     then
        rm *.md5
     fi
     COUNTTMP=`find * -maxdepth 0 -type f -name "*.tmp*"| wc -l`
     if [ "$COUNTTMP" -gt 0 ]
     then
        rm *.tmp*
     fi
     for j in `find * -maxdepth 0 -type f`
     do
        echo $j
        sha1sum $j >> $j.sha1
        md5sum $j >> $j.md5
     done
  fi
done
echo Done!

