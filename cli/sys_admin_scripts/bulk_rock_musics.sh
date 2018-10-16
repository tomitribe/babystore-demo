#!/bin/bash

while IFS=, read -r col1 col2 col3
do
	baby musics add-music --name "$col1" --singer "$col2" --category "$col3"
done < rock_musics.csv
