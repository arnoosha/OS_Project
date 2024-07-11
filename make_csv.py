import csv

# make processors.csv
processors = [
    {"processor_id": "1","cache": "8", "memory": "16", "frequency": "5"},
    # {"processor_id": "2", "cache": "7", "memory": "16", "frequency": "4"},
    # {"processor_id": "3","cache": "5", "memory": "10", "frequency": "7"},
    # {"processor_id": "4","cache": "4", "memory": "16", "frequency": "3"},
]

csv_file = "processors3.csv"


headers = processors[0].keys()

with open(csv_file, mode='w', newline='') as file:
    writer = csv.DictWriter(file, fieldnames=headers)

    writer.writeheader()

    writer.writerows(processors)

print(f"Data has been written to {csv_file}")





# make tasks.csv

#
# tasks = [
#     {"task_id": "1","arrival_time":"0","deadline":"2", "value":"200", "cache_requirement": "6",
#      "memory_requirement": "14", "frequency_requirement": "4"},
#
#     {"task_id": "2", "arrival_time": "0", "deadline": "1",
#      "value": "10", "cache_requirement": "6", "memory_requirement": "14", "frequency_requirement": "4"},
#
#     {"task_id": "3", "arrival_time": "1", "deadline": "3",
#      "value": "5", "cache_requirement": "6", "memory_requirement": "14", "frequency_requirement": "4"},
#
#     {"task_id": "4", "arrival_time": "1", "deadline": "4",
#      "value": "200", "cache_requirement": "6", "memory_requirement": "14", "frequency_requirement": "4"},
#
#     {"task_id": "5", "arrival_time": "1", "deadline": "4",
#      "value": "150", "cache_requirement": "5",
#      "memory_requirement": "9", "frequency_requirement": "5"},
#
#     # {"task_id": "6", "arrival_time": "2", "deadline": "5", "value": "60", "cache_requirement": "4",
#     #  "memory_requirement": "15", "frequency_requirement": "3"},
#
# ]
#
# csv_file = "tasks3.csv"
#
#
# headers = tasks[0].keys()
#
# with open(csv_file, mode='w', newline='') as file:
#     writer = csv.DictWriter(file, fieldnames=headers)
#
#     writer.writeheader()
#
#     writer.writerows(tasks)
#
# print(f"Data has been written to {csv_file}")
