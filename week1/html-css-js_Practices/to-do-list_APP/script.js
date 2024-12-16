const todoForm = document.getElementById('todo-form');
const todoInput = document.getElementById('todo-input');
const todoList = document.getElementById('todo-list');

todoForm.addEventListener('submit', function (event) {
    event.preventDefault();
    const newTask = todoInput.value;

    if (newTask === '') {
        alert('Please enter a task!');
        return;
    }

    // Additional code to add the task will go here

    todoInput.value = ''; // Clear the input field after adding a task
    addTask(newTask);
});



function addTask(task, completed=false) {
    const listItem = document.createElement('li');
    const taskText = document.createElement('span');
    taskText.textContent = task;
    listItem.appendChild(taskText);

    const checkBox = document.createElement('input');
    checkBox.setAttribute('type', 'checkbox');
    listItem.appendChild(checkBox);

    if(completed){
        taskText.style.textDecoration = 'line-through';
        checkBox.checked = true;
    }

    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Delete';
    deleteButton.setAttribute('class', 'deleteBtn');
    listItem.appendChild(deleteButton);

    todoList.appendChild(listItem);

    const editButton = document.createElement('button');
    editButton.textContent = 'Edit';
    editButton.setAttribute('class', 'editBtn');
    listItem.appendChild(editButton);

    checkBox.addEventListener('change', function () {
        if (this.checked) {
            taskText.style.textDecoration = 'line-through';
        } else {
            taskText.style.textDecoration = 'none';
        }
        saveTasksToLocalStorage();
    });

    deleteButton.addEventListener('click', function () {
        todoList.removeChild(listItem);
        saveTasksToLocalStorage();
    });

    editButton.addEventListener('click', function () {
        const isEditing = listItem.classList.contains('editing');

        if (isEditing) {
            // Switch back to view mode // Assuming the input field is right before the edit button
            listItem.classList.remove('editing');
            taskText.textContent = listItem.firstChild.value;
            listItem.removeChild(listItem.firstChild);
            listItem.insertBefore(taskText, listItem.firstChild);
            editButton.textContent = 'Edit';
        } else {
            // Switch to edit mode
            const input = document.createElement('input');
            input.type = 'text';
            input.value = taskText.textContent;
            listItem.insertBefore(input, taskText);
            listItem.removeChild(taskText);
            listItem.classList.add('editing');
            editButton.textContent = 'Save';
        }
        saveTasksToLocalStorage();
    });
    saveTasksToLocalStorage();
}

function saveTasksToLocalStorage() {
    const tasks = [];
    document.querySelectorAll('#todo-list li').forEach(task => {
        const taskText = task.querySelector('span').textContent;
        const isCompleted = task.querySelector('input[type="checkbox"]').checked;
        tasks.push({ text: taskText, completed: isCompleted });
    });
    localStorage.setItem('tasks', JSON.stringify(tasks));
  }
  
  document.addEventListener('DOMContentLoaded', function() {
    const savedTasks = JSON.parse(localStorage.getItem('tasks')) || [];
    savedTasks.forEach(task => {
        addTask(task.text, task.completed);
    });
  });