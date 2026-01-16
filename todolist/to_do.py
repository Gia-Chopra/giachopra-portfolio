tasks = []
#intialisation of empty list to store tasks  

def add_task(): 
    num_tasks = int(input("How many tasks would you like to input? "))

    i = 0

    while i < num_tasks:
        for task in range(num_tasks):
            task = input(f"Enter the task {i+1}: ")

            dict = {"Task": task.capitalize(), "Status": "Incomplete"}
            #creates a dicitonary which stores task name 
            # automatically assigns the incomplete status to the task

            tasks.append(dict)
            #appends to task list 

            i+=1

    print("\nTask(s) added successfully!\n")

def edit_task(): 
    show_tasks()

    len_tasks = len(tasks)
    #computes the length of the task 

    if len_tasks == 1: 
            task_change = 1
    else: 
            task_change = int(input(f"Which task would you like to edit? [1 - {len_tasks}] "))
            #asks users what task they woud like to edit 

    edited_task = input("Enter the edited task: ")

    n = tasks[task_change - 1]
    #finds nth task in list

    n["Task"] = edited_task.capitalize() 
    #updates dictionary with edited task 
    
    print("\nTask edited successfully! \n")


def status_task(): 
    show_tasks()

    len_tasks = len(tasks)

    if len_tasks == 1: 
        status_change = 1
    else: 
        status_change = int(input(f"Which task's status would you like to change? [1 - {len_tasks}] "))
        #asks users what tasks status they would like to change 

    status = input("What is the status of the task? [Incomplete (I), In Progress (P), Complete (C)] ")

    if status.capitalize() == "I":
        n = tasks[status_change - 1] 
        #finds nth task in list

        n["Status"] = "Incomplete"
        #updates dictionary with new status 
    
    elif status.capitalize() == "P":
        n = tasks[status_change - 1] 

        n["Status"] = "In Progress"
    
    elif status.capitalize() == "C":
        n = tasks[status_change - 1] 

        n["Status"] = "Completed"

    print("\nStatus successfully updated!\n")

def show_tasks(): 
    print("\nCurrent tasks:")

    if len(tasks) == 0: 
        print("no tasks :(")
    else: 
        i = 0
        while i < len(tasks): 
            for task in tasks: 
                print(f"{i+1}: {task}")
                #prints each task on a new line 
                i += 1

    print()


if __name__ == "__main__": 
    #user interaction
    print("\nWelcome to the To-Do List App! :) \n")

    while True:
        print("Please select one of the below options")

        print('''
              1. Add Task(s)
              2. Edit Task
              3. Edit status of Task 
              4. Show Tasks 
              5. Exit \n''')

        choice = input("Enter your choice: ")
        print()
        #depending on what user inputs, respective function will be called 
        if choice == "1": 
            add_task()

        elif choice == "2":
            edit_task()

        elif choice == "3": 
            status_task()

        elif choice == "4": 
            show_tasks()

        elif choice == "5": 
            break

        else: 
            print("invalid choice")