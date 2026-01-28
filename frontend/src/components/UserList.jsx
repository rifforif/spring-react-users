import React, { useState, useEffect } from 'react';
import UserService from '../services/UserService';
import { Link } from 'react-router-dom';

const UserList = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        retrieveUsers();
    }, []);

    const retrieveUsers = () => {
        UserService.getAllUsers()
            .then(response => {
                setUsers(response.data);
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
    };

    const deleteUser = (id) => {
        UserService.deleteUser(id)
            .then(response => {
                console.log(response.data);
                retrieveUsers();
            })
            .catch(e => {
                console.log(e);
            });
    };

    return (
        <div className="container">
            <h2 className="text-center my-4">Liste des Utilisateurs</h2>
            <Link to="/add" className="btn btn-primary mb-3">Ajouter un utilisateur</Link>
            <div className="table-responsive">
                <table className="table table-bordered table-striped">
                    <thead className="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Email</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map(user => (
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.nom}</td>
                                <td>{user.email}</td>
                                <td>
                                    <Link to={`/users/${user.id}`} className="btn btn-info btn-sm mr-2">Modifier</Link>
                                    <button onClick={() => deleteUser(user.id)} className="btn btn-danger btn-sm">Supprimer</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default UserList;
