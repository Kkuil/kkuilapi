import React from 'react';

export default function Tag({ children, color }) {
  return (
    <span className="tag" style={{ fontSize: "13px", padding: "2px 8px", border: `1px solid #fefefe`, borderRadius: "8px",display: "inline-block", background: color }}>
      {children}
    </span>
  );
}