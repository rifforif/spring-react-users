import React, { useState, useEffect } from 'react';
import UserService from '../services/UserService';
import { useNavigate, useParams } from 'react-router-dom';

const UserForm = () => {
    const [user, setUser] = useState({
        id: null,
        nom: '',
        email: ''
    });
    const [submitted, setSubmitted] = useState(false);
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) {
            UserService.getUserById(id)
                .then(response => {
                    setUser(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        }
    }, [id]);

    const handleInputChange = event => {
        const { name, value } = event.target;
        setUser({ ...user, [name]: value });
    };

    const saveUser = () => {
        var data = {
            nom: user.nom,
            email: user.email
        };

        if (user.id) {
            UserService.updateUser(user.id, data)
                .then(response => {
                    setSubmitted(true);
                    console.log(response.data);
                    navigate('/');
                })
                .catch(e => {
                    console.log(e);
                });
        } else {
            UserService.createUser(data)
                .then(response => {
                    setSubmitted(true);
                    console.log(response.data);
                    navigate('/');
                })
                .catch(e => {
                    console.log(e);
                });
        }
    };

    return (
        <div className="submit-form container">
            {submitted ? (
                <div>
                    <h4>You submitted successfully!</h4>
                    <button className="btn btn-success" onClick={() => setSubmitted(false)}>
                        Add
                    </button>
                </div>
            ) : (
                <div>
                    <h2 className="my-4">{user.id ? 'Modifier Utilisateur' : 'Ajouter Utilisateur'}</h2>
                    <div className="form-group">
                        <label htmlFor="nom">Nom</label>
                        <input
                            type="text"
                            className="form-control"
                            id="nom"
                            required
                            value={user.nom}
                            onChange={handleInputChange}
                            name="nom"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input
                            type="text"
                            className="form-control"
                            id="email"
                            required
                            value={user.email}
                            onChange={handleInputChange}
                            name="email"
                        />
                    </div>

                    <button onClick={saveUser} className="btn btn-success mt-3">
                        Soumettre
                    </button>
                </div>
            )}
        </div>
    );
};

export default UserForm;
