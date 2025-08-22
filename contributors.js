const fs = require('fs');
const https = require('https');
const path = require('path');

const owner = 'SidharthMudgil';
const repo = 'mini-projects';

const options = {
  hostname: 'api.github.com',
  path: `/repos/${owner}/${repo}/contributors`,
  method: 'GET',
  headers: {
    'User-Agent': 'node.js',
    'Accept': 'application/vnd.github.v3+json'
  }
};

https.get(options, res => {
  let data = '';

  res.on('data', chunk => data += chunk);
  res.on('end', () => {
    try {
      const contributors = JSON.parse(data);

      if (!Array.isArray(contributors)) {
        console.error('❌ Unexpected response:', contributors.message || 'Unknown error');
        process.exit(1);
      }

      const markdown = contributors.map(user =>
        `[<img src="${user.avatar_url}" width="50px;" alt="${user.login}"/>](https://github.com/${user.login})`
      ).join(' ');

      const grid = `## Contributors\n\n${markdown}\n\nMade with ❤️ by awesome people.`;

      const readmePath = path.resolve(__dirname, 'README.md');
      let readme = fs.readFileSync(readmePath, 'utf8');

      const startTag = '<!-- CONTRIBUTORS:START -->';
      const endTag = '<!-- CONTRIBUTORS:END -->';

      const pattern = new RegExp(`${startTag}[\\s\\S]*?${endTag}`, 'g');
      const replacement = `${startTag}\n${grid}\n${endTag}`;

      if (!readme.includes(startTag) || !readme.includes(endTag)) {
        console.warn('⚠️ Placeholder tags not found in README.md');
        process.exit(1);
      }

      const updatedReadme = readme.replace(pattern, replacement);
      fs.writeFileSync(readmePath, updatedReadme);
      console.log('✅ README.md updated with contributor grid');
    } catch (err) {
      console.error('❌ Failed to generate contributors:', err.message);
      process.exit(1);
    }
  });
}).on('error', err => {
  console.error('❌ HTTPS request failed:', err.message);
  process.exit(1);
});
