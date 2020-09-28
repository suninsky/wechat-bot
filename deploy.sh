module='wechat-bot-1.0'
hostname=wsn2019.club
rsync -ahvzz target/${module}.jar ubuntu@${hostname}:/home/ubuntu/lab-man/${module}/;