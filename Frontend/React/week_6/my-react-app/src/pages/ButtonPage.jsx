import Button from "../components/Button";
import {GoBell, GoCloudDownload, GoDatabase} from 'react-icons/go';

function ButtonPage(){
  const handleClick = ()=>{
  };
  return <div>
    <h1 className="color-red-500">hello</h1>
    <div>
      <Button danger>
        <GoBell />
        Click me!
        </Button>
    </div>
    <div>
      <Button success rounded onClick={handleClick}>
        <GoCloudDownload />
        Buy Now!
        </Button>
    </div>
    <div>
      <Button warning>
        <GoDatabase />
        See Deal!
        </Button>
    </div>
    <div>
      <Button secondary>
        Hide Ads!
        </Button>
    </div>
    <div>
      <Button primary >
        Warning!
        </Button>
    </div>
  </div>
}

export default ButtonPage;