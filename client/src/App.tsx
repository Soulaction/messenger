import { BrowserRouter } from 'react-router-dom';
import { AppRouter } from './components/AppRoter';
import Menu from './components/menu/Menu';

function App() {
  return (
      <BrowserRouter>
        <AppRouter />
        <Menu/>
      </BrowserRouter>
  );
}

export default App;
