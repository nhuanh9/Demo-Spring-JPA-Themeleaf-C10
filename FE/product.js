const API_PRODUCTS = 'http://localhost:8080/api/products';
const API_CATEGORIES = 'http://localhost:8080/api/categories';

async function loadCategories() {
    try {
        const response = await axios.get(API_CATEGORIES);
        const formSelect = document.getElementById('productCategory');
        const filterSelect = document.getElementById('filterCategory');
        
        formSelect.innerHTML = '<option value="">No Category</option>';
        filterSelect.innerHTML = '<option value="">All Categories</option>';
        
        response.data.forEach(c => {
            formSelect.innerHTML += `<option value="${c.id}">${c.name}</option>`;
            filterSelect.innerHTML += `<option value="${c.id}">${c.name}</option>`;
        });
    } catch (e) {
        console.error(e);
    }
}

async function loadProducts() {
    const search = document.getElementById('search').value;
    const categoryId = document.getElementById('filterCategory').value;
    try {
        const response = await axios.get(API_PRODUCTS, {
            params: { search, categoryId }
        });
        const tbody = document.getElementById('productList');
        tbody.innerHTML = '';
        response.data.forEach(p => {
            const catName = p.category ? p.category.name : 'N/A';
            const catId = p.category ? p.category.id : '';
            tbody.innerHTML += `
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${catName}</td>
                    <td>
                        <button onclick="editProduct(${p.id}, '${p.name}', ${p.price}, '${catId}')">Edit</button>
                        <button onclick="deleteProduct(${p.id})">Delete</button>
                    </td>
                </tr>
            `;
        });
    } catch (e) {
        console.error(e);
        alert('Error loading products');
    }
}

async function saveProduct() {
    const id = document.getElementById('productId').value;
    const name = document.getElementById('productName').value;
    const price = document.getElementById('productPrice').value;
    const categoryId = document.getElementById('productCategory').value;
    
    const payload = {
        name,
        price: parseFloat(price),
        category: categoryId ? { id: parseInt(categoryId) } : null
    };
    
    try {
        if (id) {
            await axios.put(`${API_PRODUCTS}/${id}`, payload);
        } else {
            await axios.post(API_PRODUCTS, payload);
        }
        cancelEdit();
        loadProducts();
    } catch (e) {
        console.error(e);
        alert('Error saving product');
    }
}

function editProduct(id, name, price, categoryId) {
    document.getElementById('productId').value = id;
    document.getElementById('productName').value = name;
    document.getElementById('productPrice').value = price;
    document.getElementById('productCategory').value = categoryId;
    document.getElementById('btnSave').innerText = 'Update';
    document.getElementById('btnCancel').style.display = 'inline';
}

function cancelEdit() {
    document.getElementById('productId').value = '';
    document.getElementById('productName').value = '';
    document.getElementById('productPrice').value = '';
    document.getElementById('productCategory').value = '';
    document.getElementById('btnSave').innerText = 'Save';
    document.getElementById('btnCancel').style.display = 'none';
}

async function deleteProduct(id) {
    if (confirm('Delete?')) {
        try {
            await axios.delete(`${API_PRODUCTS}/${id}`);
            loadProducts();
        } catch (e) {
            console.error(e);
            alert('Error deleting product');
        }
    }
}

async function init() {
    await loadCategories();
    await loadProducts();
}
init();
