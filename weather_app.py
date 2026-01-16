#requests is a module which allows implenetation of API 
import requests

#weather API for weather data 
weather_api = '3d6b86601cb5045d981652c677f5e9d8'

def get_weather(city):
    #variable uses request library to fetch url, response is contained in weather_data variable 
    weather_data = requests.get(
        f"https://api.openweathermap.org/data/2.5/weather?q={user_input}&units=metric&APPID={weather_api}")

    if weather_data.status_code == 200: 
       #if the status code is equal to 200, the city has been found in the API
       return weather_data.json()
        #.json() allows all the data from the api to be stored and transported

    elif weather_data.status_code == 404: 
        #if the status code is 404, it means the city has NOT been found, following statement is printed
        print("City could not be found")
        return None
    
def display_weather(data): 

    if data:
        #if data has been transported (city is found), the city name, weather and temperature has been extracted from the dictionary 
        city = data['name']

        weather = data['weather'][0]['main']

        temp = round(data['main']['temp'])

        return f"In {city}, the weather is {weather} at {temp}°C."\
    
    else:
        return 

if __name__ == '__main__': 
    print("Welcone to the weather app!")

    #ask user which city they would like to see the weather for
    user_input = input("Please enter a city: ")

    #variable data contains ALL the stored data from the API 
    data = get_weather(user_input)

    #contains specific data which users request to view
    user_info = display_weather(data)

    print(user_info)




# import random

# #chance_of_rain = random.randint(0, 100)
# chance_of_rain = 100

# if chance_of_rain == 0: 
#     cloudy = input("Is it cloudy? (True/False) ") 

#     if cloudy == True: 
#         print("It is a cloudy day with no chance of rain.")
#     else: 
#         print("It is a sunny day with no chance of rain.")

# elif chance_of_rain < 100: 
#     print(f"there is a {chance_of_rain}% chance of rain.")

# else: 
#     print("it is certain to rain")

