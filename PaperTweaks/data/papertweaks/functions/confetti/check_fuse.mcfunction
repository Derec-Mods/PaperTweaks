execute store result score #fuse pt_fuse run data get entity @s Fuse
execute if score #fuse pt_fuse matches 1 run function papertweaks:confetti/explode
scoreboard players set #fuse pt_fuse 0
execute store result score #rand pt_rand run data get entity @s UUID[0]
scoreboard players operation #rand pt_rand %= #total pt_rand