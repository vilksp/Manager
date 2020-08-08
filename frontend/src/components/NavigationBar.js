import React from 'react';
import { Navbar,Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';

class NavigationBar extends React.Component {

        render() {
    
            return (
    
                <Navbar bg="warning" variant="dark">
                    <Link to={""} className="navbar-brand">
                        <Navbar.Brand href="#home"> Home</Navbar.Brand>
                    </Link>
    
                    <Nav className="mr-auto">
                        <Link className="nav-link" to="/about">About</Link>

                    <Nav className="mr-auto">
                        <Link className="nav-link" to="/users">Users</Link>
                    </Nav>

                    <Nav className="mr-auto">
                        <Link className="nav-link" to="/test">Test</Link>
                    </Nav>

                    <Nav className="mr-auto">
                        <Link className="nav-link" to="/image">Image</Link>
                    </Nav>
                </Nav>

                    
                </Navbar>
    
    
            )
        }
    }
    export default NavigationBar;