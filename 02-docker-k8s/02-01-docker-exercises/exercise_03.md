# Nginx Modify Html

1. Use the same container of the last exercise or just create a new container with the nginx image.
2. The goal is to change the title from “Welcome to nginx!” to “Welcome to Code Academy!”. For this check the file you edited in the last exercise (etc/nginx/conf.d/default.conf) and check on the location configuration block the directory where you can find the index.html
3. Edit the file using vim (The image used on the previous file has already vim installed, if not, please install it using “apt-get”) and change the title to “Welcome to Code Academy!”.
4. Refresh the browser to see the change. Because of cache you might need to do it multiple times.