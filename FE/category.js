const API_URL = 'http://localhost:8080/api/categories';

async function loadCategories() {
    const search = document.getElementById('search').value;
    try {
        const response = await axios.get(API_URL, { params: { search } });
        const tbody = document.getElementById('categoryList');
        tbody.innerHTML = '';
        response.data.forEach(c => {
            tbody.innerHTML += `
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td>
                        <button onclick="editCategory(${c.id}, '${c.name}')">Edit</button>
                        <button onclick="deleteCategory(${c.id})">Delete</button>
                    </td>
                </tr>
            `;
        });
    } catch (e) {
        console.error(e);
        alert('Error loading categories');
    }
}

async function saveCategory() {
    const id = document.getElementById('categoryId').value;
    const name = document.getElementById('categoryName').value;
    try {
        if (id) {
            await axios.put(`${API_URL}/${id}`, { name });
        } else {
            await axios.post(API_URL, { name });
        }
        cancelEdit();
        loadCategories();
    } catch (e) {
        console.error(e);
        alert('Error saving category');
    }
}

function editCategory(id, name) {
    document.getElementById('categoryId').value = id;
    document.getElementById('categoryName').value = name;
    document.getElementById('btnSave').innerText = 'Update';
    document.getElementById('btnCancel').style.display = 'inline';
}

function cancelEdit() {
    document.getElementById('categoryId').value = '';
    document.getElementById('categoryName').value = '';
    document.getElementById('btnSave').innerText = 'Save';
    document.getElementById('btnCancel').style.display = 'none';
}

async function deleteCategory(id) {
    if (confirm('Delete?')) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            loadCategories();
        } catch (e) {
            console.error(e);
            alert('Error deleting category');
        }
    }
}

loadCategories();
