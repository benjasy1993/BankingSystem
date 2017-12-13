FROM python:2
ADD ./ /frontend
WORKDIR /frontend
RUN pip install -r requirements.txt
EXPOSE 8000
CMD ["python", "manage.py", "runserver"]